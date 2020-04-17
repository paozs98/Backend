import React, { useState } from 'react';
import { Form, Input, Button, Card, message} from 'antd';
import { useHistory } from "react-router-dom";
import './login.css';


function Login () {
    const [cedula, setCedula] = useState('');
    const [contrasena, setContrasena] = useState('');
    let history = useHistory();

    const layout = {
        labelCol: {
          span: 8,
        },
      };
    
      const tailLayout = {
        wrapperCol: {
          span: 8,
        },
      };
    
  const login = async () => {
        if (cedula === '' || contrasena === '') {
          message.warning('Por favor ingrese sus credenciales');
        }
        else{
            fetch('http://localhost:8080/matriculaBackend/UsuarioServlet?cedula='+cedula+'&contrasena='+contrasena, { method: 'GET'})
            .then((res) => res.json())
            .then((result) => {
                if(result.usuario !== 'null') {
                    localStorage.setItem('user', cedula);
                    history.push('/home') 
                }
                else {
                    message.error('Credenciales incorrectas');
                }
            })
        }
  };

  return (
    <div className="site-card-border-less-wrapper">
    <Card style={{justifyContent: 'center'}} title = 'Inicio de sesión' bordered={false} >
    <Form {...layout}>
      <Form.Item className='input' {...tailLayout} label="Cédula" name="username"
        rules={[
          {
            required: true,
            message: 'Por favor ingrese su cédula!',
          },
        ]}
      >
        <Input onChange={e => {setCedula(e.target.value)}} />
      </Form.Item>

      <Form.Item className='input' {...tailLayout} label="Contraseña" name="password"
        rules={[
          {
            required: true,
            message: 'Por favor ingrese su contraseña!',
          },
        ]}
      >
        <Input.Password onChange={e => {setContrasena(e.target.value)}}/>
      </Form.Item>
        <Button type="primary" id='button' onClick={login}>
          Ingresar
        </Button>
    </Form>
    </Card>
    </div>
  );
};

export default Login