import axios from 'axios';
import { useState } from 'react';
import './index.css'
import { Navigate, NavLink, useNavigate } from 'react-router-dom';
import Swal from "sweetalert2";
 function Form() {
    const navigate = useNavigate();
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const [submitted, setSubmitted] = useState(false);
    const [error, setError] = useState(false);

    const handleName = (e) => {
        setName(e.target.value);
        setSubmitted(false);
    };

    const handleEmail = (e) => {
        setEmail(e.target.value);
        setSubmitted(false);
    };

    const handlePassword = (e) => {
        setPassword(e.target.value);
        setSubmitted(false);
    };

    // Handling the form submission
    const handleSubmit = async (e) => {
        e.preventDefault();
        if (name !== '' && email !== '' && password !== '') {
            setError(true);
            const body = {
                'fullName': name, 'emailID': email, 'password': password
            }
            const result = await axios.post("http://localhost:8081/registerUser", body);
            console.log(result);
            if (result['status'] === 200) {
                Swal.fire({
                    icon: "success",
                    title: "Results",
                    text: `User ID is ` + result['data'],
                    showConfirmButton: true,
                    //timer: 1500

                })
            }

        } else {
            console.log('Inside else');
            setSubmitted(true);
            setError(true);

            Swal.fire({
                icon: "error",
                title: "Results",
                text: 'Please enter all the fields.',
                showConfirmButton: true,
                //timer: 1500

            })
        }
    };
    const handleLogin = () => {
        navigate("/");
    }

    const successMessage = () => {
        return (
            <div
                className="success"
                style={{
                    display: submitted ? '' : 'none',
                }}>
                <h1>User {name} successfully registered!!</h1>
            </div>
        );
    };

    const errorMessage = () => {
        return (
            <div
                className="error"
                style={{
                    display: error ? '' : 'none',
                }}>
                <h1>Please enter all the fields</h1>
            </div>
        );
    };

    return (
        <div className="form">
            <div>
                <h1>User Registration</h1>
            </div>



            <form className="forms">
                <div>
                <label className="label">Name</label>
                <input style={{width: "370px"}} onChange={handleName} className="input"
                    value={name} type="text" />

                <label className="label">Email</label>
                <input style={{width: "370px"}} onChange={handleEmail} className="input"
                    value={email} type="email" />

                <label className="label">Password</label>
                <input onChange={handlePassword} className="input"
                    value={password} type="password" />
                </div>
                &nbsp;&nbsp;&nbsp;
                <button onClick={handleSubmit} className="btn" type="submit">
                    Submit
                </button>
                &nbsp;&nbsp;&nbsp;
                <button onClick={handleLogin} className="btn" type="submit">
                    Login
                </button>
            </form>
        </div>
    );
}
export default Form;