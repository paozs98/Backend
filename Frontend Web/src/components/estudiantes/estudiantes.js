import React, { useState, useEffect } from 'react';
import { Table, Tooltip, Button, InputNumber, Popconfirm, Form,Breadcrumb,Menu, Layout, message} from 'antd';
import { ArrowLeftOutlined, EditOutlined, CloseOutlined, CheckOutlined } from '@ant-design/icons';
import { useHistory } from "react-router-dom";
import '../layout.css';
const { Header, Content, Footer } = Layout;

const EditableCell = ({
  editing,
  dataIndex,
  title,
  inputType,
  record,
  index,
  children,
  ...restProps
}) => {
  return (
    <td {...restProps}>
      {editing ? (
        <Form.Item
          name={dataIndex}
          style={{
            margin: 0,
          }}
          rules={[
            {
              required: true,
              message: `Por favor ingrese una ${title}!`,
            },
          ]}
        >
           <InputNumber />
        </Form.Item>
      ) : (
        children
      )}
    </td>
  );
};

const Estudiantes = () => {
  const [form] = Form.useForm();
  const [alumnos, setAlumnos] = useState([]);
  const [editingKey, setEditingKey] = useState('');
  let history = useHistory();

  const isEditing = record => record.no_matricula === editingKey;

  useEffect(() => {
    fetch('http://localhost:8080/matriculaBackend/MatriculaServlet?id_grupo=' + history.location.state.grupo.numeroGrupo, { method: 'GET'})
    .then((res) => res.json())
    .then((result) => {
        (result.alumnos !== 'null') ? setAlumnos(result.alumnos) : message.error('El grupo no tiene estudiantes matriculados');
    })
  }, [history.location.state.grupo])

  const edit = record => {
    form.setFieldsValue({ ...record });
    setEditingKey(record.no_matricula);
  };

  const logout = () => {
    localStorage.removeItem('user');
    history.push('/')
  }

  const cancel = () => {
    setEditingKey('');
  };

  const reloadAlumnos = () => {
    fetch('http://localhost:8080/matriculaBackend/MatriculaServlet?id_grupo=' + history.location.state.grupo.numeroGrupo, { method: 'GET'})
    .then((res) => res.json())
    .then((result) => {
        (result.alumnos !== 'null') ? setAlumnos(result.alumnos) : message.error('El grupo no tiene estudiantes matriculados');
    })
  }

  const save = async key => {
      const row = await form.validateFields();
      fetch('http://localhost:8080/matriculaBackend/MatriculaServlet?no_matricula=' + key.no_matricula+'&nota='+row.nota, { method: 'POST'})
        .then((res) => res.json())
        .then((result) => {
            if (result.registro) {
              setEditingKey('');
              reloadAlumnos();
              message.success('La nota fue registrada exitosamente');
            }
            else {
              message.error('La nota no pudo ser actualizada');
            }
        });
  };

  const columns = [
    {
      title: 'Cédula',
      align: 'center',
      render: (_, record) => record.alumno.cedula
    },
    {
      title: 'Nombre',
      align: 'center',
      render: (_, record) => record.alumno.nombre
    },
    {
      title: 'Email',
      align: 'center',
      render: (_, record) => record.alumno.email
    },
    {
      title: 'Nota',
      align: 'center',
      dataIndex: 'nota',
      editable: true,
    },
    {
      title: 'Registro de nota',
      align: 'center',
      dataIndex: 'operation',
      render: (_, record) => {
        const editable = isEditing(record);
        return editable ? (
          <span>
            <Button style={{margin: '10px'}} onClick={() => save(record)} shape="circle" icon={<CheckOutlined />}/>
            <Popconfirm title="Seguro que desea cancelar?" onConfirm={cancel}>
            <Button style={{margin: '10px'}} shape="circle" icon={<CloseOutlined />}/>
            </Popconfirm>
          </span>
        ) : (
          <Tooltip title="Listado de estudiantes">
              <Button disabled={editingKey !== ''} onClick={() => edit(record)} shape="circle" icon={<EditOutlined />}/>
          </Tooltip>
        );
      },
    },
  ];
  const mergedColumns = columns.map(col => {
    if (!col.editable) {
      return col;
    }

    return {
      ...col,
      onCell: record => ({
        record,
        inputType: 'number',
        dataIndex: col.dataIndex,
        title: col.title,
        editing: isEditing(record),
      }),
    };
  });
  return (
    <Layout className="layout">
      <Header>
        <div className="logo" />
        <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['2']}>
          <Menu.Item onClick={e => history.push('/home')} key="1">Home</Menu.Item>
          <Menu.Item onClick={e => history.push('/grupos')} key="2">Mis grupos</Menu.Item>
          <Menu.Item key="3" onClick={logout}>Cerrar Sesión</Menu.Item>
        </Menu>
      </Header>
      <Content style={{ padding: '0 50px' }}>
        <Breadcrumb style={{ margin: '16px 0' }}>
          <ArrowLeftOutlined onClick={e => history.push('/grupos')} style={{ marginRight: '16px' }}/>
          <Breadcrumb.Item>Home</Breadcrumb.Item>
          <Breadcrumb.Item>Grupo {history.location.state.grupo.numeroGrupo}</Breadcrumb.Item>
          <Breadcrumb.Item>Estudiantes</Breadcrumb.Item>
        </Breadcrumb>
        <div className="site-layout-content">
          <h1>Estudiantes grupo  {history.location.state.grupo.numeroGrupo}</h1>
          <Form form={form} component={false}>
      <Table
        components={{
          body: {
            cell: EditableCell,
          },
        }}
        dataSource={alumnos}
        columns={mergedColumns}
        rowClassName="editable-row"
      />
    </Form>
        </div>
      </Content>
      <Footer style={{ textAlign: 'center' }}></Footer>
    </Layout>
  );
};

export default Estudiantes;
