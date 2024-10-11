import React, {useState} from 'react';
import {register} from '../services/apiService';
import '../styles/Register.css';

const Register = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        email: ''
    });

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            const response = await register(formData);
            console.log('Registration successful', response);
            // Handle successful registration (redirect to login)
        } catch (error) {
            console.error('Registration failed', error);
            // Handle registration error (show error message)
        }
    };

    return (
        <form onSubmit={handleRegister} className="register-form">
            <div className="form-group">
                <label htmlFor="username">Username</label>
                <input type="text" id="username" name="username" value={formData.username} onChange={handleChange}
                       placeholder="Username" required/>
            </div>
            <div className="form-group">
                <label htmlFor="password">Password</label>
                <input type="password" id="password" name="password" value={formData.password} onChange={handleChange}
                       placeholder="Password" required/>
            </div>
            <div className="form-group">
                <label htmlFor="email">Email</label>
                <input type="email" id="email" name="email" value={formData.email} onChange={handleChange}
                       placeholder="Email" required/>
            </div>
            <button type="submit">Register</button>
        </form>
    );
};

export default Register;