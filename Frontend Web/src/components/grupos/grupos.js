import React, { useState, useEffect } from 'react';
import { Layout, Menu, Breadcrumb, Table, message, Button, Tooltip } from 'antd';
import { UnorderedListOutlined } from '@ant-design/icons';
import { useHistory } from "react-router-dom";
import '../layout.css';
const { Header, Content, Footer } = Layout;

function Grupos () {
    const [grupos, setGrupos] = useState([]);
    let history = useHistory();
    
      const columns = [
        { title: 'Número de grupo',align: 'center', dataIndex: 'numeroGrupo', key: 'numeroGrupo' },
        { title: 'Ciclo', align: 'center',dataIndex: 'no_ciclo', key: 'no_ciclo' },
        { title: 'Curso', align: 'center',dataIndex: 'codigo_curso', key: 'codigo_curso' },
        { title: 'Horario', align: 'center',dataIndex: 'horario', key: 'horario' },
        { title: 'Estudiantes', align: 'center', key: 'estudiantes', 
          render: (_, record) => 
            <Tooltip title="Listado de estudiantes">
              <Button onClick={(e) => history.push({pathname: '/estudiantes', state: {grupo: record}})} shape="circle" icon={<UnorderedListOutlined />} />
          </Tooltip>
        },
      ];

      useEffect(() => {
        fetch('http://localhost:8080/matriculaBackend/GrupoServlet?cedula='+localStorage.getItem('user'), { method: 'GET'})
        .then((res) => res.json())
        .then((result) => {
            (result.grupos !== 'null') ? setGrupos(result.grupos) : message.error('No tiene grupos asignados');
        })
      }, [])

      const logout = () => {
        localStorage.removeItem('user');
        history.push('/')
      }
    
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
        <Breadcrumb.Item>Home</Breadcrumb.Item>
        <Breadcrumb.Item>Grupos</Breadcrumb.Item>
      </Breadcrumb>
      <div className="site-layout-content">
        <h1>Grupos</h1>
        <Table
      className="components-table"
      columns={columns}
      dataSource={grupos}
    />
    </div>
    </Content>
    <Footer style={{ textAlign: 'center' }}></Footer>
  </Layout>
  );
};

export default Grupos