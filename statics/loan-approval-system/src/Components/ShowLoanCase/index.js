import React, { useState, useRef, useEffect } from 'react'
import axios from "axios";
import Select from "react-select";
import { NavLink, useNavigate } from 'react-router-dom';
import './index.css'

function ShowCase() {
    const navigate = useNavigate()
  const [id, setId] = useState('');
 const [show,setShow]=useState(false);
  const textInput = useRef(null);
  let salary=null;
    let loan_amnt=null;
    let term=45;
    let empl_length=null;
    let home_ownersh=null;
    let purpose=null;
    let city=null;
    let dti=null;
    let delinq=null;
    let revol=null
    let totalacc=null;
    let prevcreditlength=null;
    let verificationStatus=null;
    let loan_verification=null;
    let confidence=null;
    let interest_rate=null;
  const handleFetch = async (e) => {
      e.preventDefault();
      console.log(id);
      setShow(true);
      const userData = await axios.get("http://localhost:8081/getCase", {
          params: {
              "caseID": id
          }

      });
     
      let loanCase;
      loanCase=userData['data']
      loan_amnt=loanCase['loan_amnt']
      term=loanCase['term']
      empl_length=loanCase['emp_length']
      home_ownersh=loanCase['home_ownership']
      salary=loanCase['annual_inc']
      purpose=loanCase['purpose']
      revol=loanCase['revol_util']
      dti=loanCase['dti']
      totalacc=loanCase['total_acc']
      prevcreditlength=loanCase['longest_credit_length']
      verificationStatus=loanCase['verification_status']
      city=loanCase['addr_state']
      delinq=loanCase['delinq_2yrs']
      loan_verification=loanCase['prediction']
      interest_rate=loanCase['interest_rate']
      confidence=loanCase['confidence'];


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
        <div><p>Loan ID-{id}</p>
             <p>Loan Amount- {loan_amnt}</p>
             <p>Term- {term}</p>
             <p>Employee Length- {empl_length}</p>
             <p>homeOwnership- {home_ownersh}</p>
             <p>Salary- {salary}</p>
             <p>purpose- {purpose}</p>
             <p>Loan Amount- {loan_amnt}</p>
             <p>Loan Amount- {loan_amnt}</p>
             <p>Loan Amount- {loan_amnt}</p>
             <p>Loan Amount- {loan_amnt}</p>
             <p>Loan Amount- {loan_amnt}</p>
             <p>Loan Amount- {loan_amnt}</p>
             <p>Loan Amount- {loan_amnt}</p>
        </div> }
    </div>
  )
}

export default ShowCase;