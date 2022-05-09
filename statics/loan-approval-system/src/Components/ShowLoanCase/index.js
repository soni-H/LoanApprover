import React, { useState, useRef, useEffect } from 'react'
import axios from "axios";
import Select from "react-select";
import { NavLink, useNavigate } from 'react-router-dom';
import './index.css'
import Swal from "sweetalert2";

function ShowCase() {
    const navigate = useNavigate()
  const [id, setId] = useState('');
 const [show,setShow]=useState(false);
  const textInput = useRef(null);
    const [salary, setSalary] = useState('');
    const [loan_amnt,setLoan_amnt] = useState('');
    const [term,setTerm] = useState('');
    const [empl_length,setEmpl_length] = useState('');
    const [home_ownersh,setHome_ownersh] = useState('');
    const [purpose,setPurpose] = useState('');
    const [city,setCity] = useState('');
    const [dti,setDti] = useState('');
    const [delinq,setDelinq] = useState('');
    const [revol,setrevol] = useState('');
    const [prevcreditlength,setprevcreditlength] = useState('');
    const [prediction,setprediction] = useState('');

    const [loan_verification,setloan_verification] = useState('');
    const [confidence,setconfidence] = useState('');
    const [interest_rate,setinterest_rate] = useState('');
    const [total_acc,setTotal_acc] = useState('');

  const handleFetch = async (e) => {
      e.preventDefault();
      console.log(id);
    
      const userData = await axios.get("http://localhost:8081/getAll/"+id

      );
      let loanCase;
      loanCase=userData['data']
     if(loanCase === ''){
         setShow(false);
         Swal.fire({
             icon: "error",
             title: "Results",
             text: `No such record exists!`,
             showConfirmButton: true,
             //timer:1500

         })
         return;
     }
       setShow(true);

      setLoan_amnt(loanCase['loan_amnt']);
      setTerm(loanCase['term']);
      setEmpl_length(loanCase['emp_length']);
      setHome_ownersh(loanCase['home_ownership']);
      setSalary(loanCase['annual_inc']);
      setPurpose(loanCase['purpose']);
      setrevol(loanCase['revol_util']);
      setDti(loanCase['dti']);
      setTotal_acc(loanCase['total_acc']);
      setprevcreditlength(loanCase['longest_credit_length']);
      setloan_verification(loanCase['verification_status'])
      setCity(loanCase['addr_state'])
      setDelinq(loanCase['delinq_2yrs'])
      setprediction(loanCase['prediction'])
      setinterest_rate(loanCase['interest_rate'])
      setconfidence(loanCase['confidence']);
        console.log(salary);

  }
  return (
    <div>
        <form className="form">
        <label htmlFor="id">Enter User ID</label>
                <input
                    id="id"
                    type="number"
                    name="id"
                    ref={textInput}
                    value={id}
                    onChange={e => setId(e.target.value)}
                    
                />
                <button type="submit" value="Submit" onClick={handleFetch}>Submit</button>
                <button
                        style={{ marginLeft: '12px' }}
                        type="button"
                        value="Cancel"
                        onClick={() => navigate('/dashboard')}
                >Cancel</button>
        </form>
        {show &&
        <div className="form1">
           <p>salary : {salary}</p>
             <p>Loan Amount : {loan_amnt}</p>
             <p>Term : {term}</p>
             <p>Employee Length : {empl_length}</p>
             <p>Home Ownership Status : {home_ownersh}</p>
             <p>Purpose : {purpose}</p>
             <p>City : {city}</p>
             <p>Debt to income ratio : {dti}</p>
             <p>Number of misdemeanor in the past 2 years : {delinq}</p>
             <p>Revolving credit line utilized : {revol}</p>
             <p>Total accounts (number of credit lines) : {total_acc}</p>
             <p>Age of oldest active account  : {prevcreditlength}</p>
             <p>Income verification status : {loan_verification}</p>
             <p>Loan Verification status : {prediction}</p>
             <p>Confidence : {confidence}</p>
             <p>Predicted Interest Rate : {interest_rate}</p>
        </div> }
    </div>
  )
}

export default ShowCase;