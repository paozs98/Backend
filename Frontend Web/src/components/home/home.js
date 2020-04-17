import React, { useState, useEffect } from 'react';
import { Layout, Menu, Breadcrumb } from 'antd';
import { useHistory } from "react-router-dom";
import '../layout.css';
const { Header, Content, Footer } = Layout;

function Home () {
  const [profesor, setProfesor] = useState([]);
    let history = useHistory();

    const logout = () => {
      localStorage.removeItem('user');
      history.push('/')
    }
    useEffect(() => {
      fetch('http://localhost:8080/matriculaBackend/ProfesorServlet?cedula='+localStorage.getItem('user'), { method: 'GET'})
      .then((res) => res.json())
      .then((result) => {console.log(result);setProfesor({nombre: result.nombre, email: result.email, telefono: result.telefono});})
    }, [])
  return (
    <Layout className="layout">
    <Header>
      <div className="logo" />
      <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['1']}>
        <Menu.Item onClick={e => history.push('/home')} key="1">Home</Menu.Item>
        <Menu.Item onClick={e => history.push('/grupos')} key="2">Mis grupos</Menu.Item>
        <Menu.Item onClick={logout} key="3">Cerrar sesión</Menu.Item>
      </Menu>
    </Header>
    <Content style={{ padding: '0 50px' }}>
      <Breadcrumb style={{ margin: '16px 0' }}>
        <Breadcrumb.Item>Home</Breadcrumb.Item>
      </Breadcrumb>
      <div className="site-layout-content">
        <h1>Bienvenido!</h1>
        <h2>{profesor.nombre}</h2>
        <h4>{profesor.email}</h4>
      </div>
    </Content>
    <Footer style={{ textAlign: 'center' }}></Footer>
  </Layout>
    
  );
};

export default Home