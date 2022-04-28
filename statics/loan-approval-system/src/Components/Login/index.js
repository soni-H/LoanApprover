import React, { useState } from "react";
import ReactDOM from "react-dom";
import "./index.css";
import { NavLink, useNavigate } from 'react-router-dom';
import axios from 'axios'
function Login() {
  // React States
  const navigate = useNavigate();
  const [errorMessages, setErrorMessages] = useState({});
  const [isSubmitted, setIsSubmitted] = useState(false);
  //Routing
  // User Login info
  const database = [
    {
      username: "user1",
      password: "pass1"
    },
    {
      username: "user2",
      password: "pass2"
    }
  ];

  const errors = {
    uname: "invalid username",
    pass: "invalid password"
  };

  const handleSubmit = async(event) => {
    //Prevent page reload
    event.preventDefault();
    
    var { uname, pass } = document.forms[0];

    // Find user login info
    const userData = await axios.get("http://app:8081/predictLoan",{"username":uname,"password":pass});

    // Compare user info
    if (userData) {
      setIsSubmitted(true);
      console.log(uname,pass);
      navigate('/Dashboard');
    } else {
      // Username not found
      setErrorMessages({ name: "uname", message: errors.uname });
    }
  };

  // Generate JSX code for error message
  const renderErrorMessage = (name) =>
    name === errorMessages.name && (
      <div className="error">{errorMessages.message}</div>
    );
  const handleSignup=() =>{
    navigate('/signup');
  }
  // JSX code for login form
  const renderForm = (
    <div className="form">
      <form onSubmit={handleSubmit}>
        <div className="input-container">
          <label>Username </label>
          <input type="text" name="uname" required />
          {renderErrorMessage("uname")}
        </div>
        <div className="input-container">
          <label>Password </label>
          <input type="password" name="pass" required />
          {renderErrorMessage("pass")}
        </div>
        <div className="button-container">
          <input type="submit" value="Login"/>
        </div>
        <div className="button-container">
        <button variant='contained' color='warning' size='small' className="round-button"  onClick={handleSignup} sx={{ mt: 8 }}>SignUp</button>
        </div>

      </form>
    </div>
  );

  return (
    <div className="app">
      <div className="login-form">
        <div className="title">Sign In</div>
        {isSubmitted ? <div>User is successfully logged in</div> : renderForm}
      </div>
    </div>
  );
}
export default Login;