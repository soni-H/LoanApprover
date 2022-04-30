import React, { useState, useRef, useEffect } from 'react'
import axios from "axios";
import Select from "react-select";
import { NavLink, useNavigate } from 'react-router-dom';

function ShowCase() {
    const navigate = useNavigate()
  const [id, setId] = useState('');

  const textInput = useRef(null);
  let salary=null;
    let loan_amnt=null;
    let term=null;
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
  const handleFetch = async () => {

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
      return(
          <div className="small-container">
                  <h1>Case Information</h1>
                  <label htmlFor="salary">Annual income ($)</label>
                  <input
                      disabled={true}
                      id="salary"
                      type="number"
                      name="salary"
                      ref={textInput}
                      value={salary}
                      //onChange={e => setSalary(e.target.value)}
                  />
                  <label htmlFor="loan_amnt">Requested loan amount ($)</label>
                  <input
                      disabled={true}
                      id="loan_amnt"
                      type="loan_amnt"
                      name="loan_amnt"
                      value={loan_amnt}
                      //onChange={e => setloan_amnt(e.target.value)}
                  />
                  <label htmlFor="term">Loan term length (months)</label>
                  <input
                      disabled={true}
                      id="term"
                      type="term"
                      name="term"
                      value={term}
                      //onChange={e => setterm(e)}
                  />
                  <label htmlFor="empl_length">Employee Length</label>
                  <input
                      disabled={true}
                      id="empl_length"
                      type="empl_length"
                      name="empl_length"
                      value={empl_length}
                      //onChange={e => setemp_length(e.target.value)}
                  />
                  <label htmlFor="home_ownersh">Housing status</label>

                  <input
                      disabled={true}
                      //id="home_ownersh"
                      //type="home_ownersh"
                      //name="home_ownersh"
                      value={home_ownersh}
                      //onChange={e => sethome_ownersh(e)}
                  />
                  <label htmlFor="purpose">Purpose of loan</label>
                  <input
                      disabled={true}
                      id="purpose"
                      type="purpose"
                      name="purpose"
                      value={purpose}
                      //onChange={e => setpurpose(e)}
                  />
                  <label htmlFor="city">State of residence</label>
                  <input
                      disabled={true}
                      id="city"
                      type="city"
                      name="city"
                      value={city}
                      //onChange={e => setcity(e)}
                  />
                  <label htmlFor="dti">Debt to income ratio</label>
                  <input
                      disabled={true}
                      id="dti"
                      type="dti"
                      name="dti"
                      value={dti}
                      //onChange={e => setdti(e.target.value)}
                  />
                  <label htmlFor="delinq">Number of misdemeanor in the past 2 years</label>
                  <input
                      disabled={true}
                      id="delinq"
                      type="delinq"
                      name="delinq"
                      value={delinq}
                      //onChange={e => setdelinq(e.target.value)}
                  />
                  <label htmlFor="revol">Revolving credit line utilized</label>
                  <input
                      disabled={true}
                      id="revol"
                      type="revol"
                      name="revol"
                      value={revol}
                      //onChange={e => setrevol(e.target.value)}
                  />
                  <label htmlFor="totalacc">Total accounts (number of credit lines)</label>
                  <input
                      disabled={true}
                      id="totalacc"
                      type="totalacc"
                      name="totalacc"
                      value={totalacc}
                      //onChange={e => settotalacc(e.target.value)}
                  />
                  <label htmlFor="prevcreditlength">Age of oldest active account</label>
                  <input
                      disabled={true}
                      id="prevcreditlength"
                      type="prevcreditlength"
                      name="prevcreditlength"
                      value={prevcreditlength}
                      //onChange={e => setprevcreditlength(e.target.value)}
                  />
                  <label htmlFor="verificationStatus">Income verification status</label>
                  <input
                      disabled={true}
                      id="verificationStatus"
                      type="verificationStatus"
                      name="verificationStatus"
                      value={verificationStatus}
                      //onChange={e => setverificationStatus(e)}
                  />
                  <label htmlFor="verificationStatus">Loan verification status</label>
                  <input
                      disabled={true}
                      id="loan_verification"
                      type="loan_verification"
                      name="loan_verification"
                      value={loan_verification}
                      //onChange={e => setloan_verification(e)}
                  />
              <label htmlFor="confidence">Confidence</label>
              <input
                  disabled={true}
                  id="confidence"
                  type="confidence"
                  name="confidence"
                  value={confidence}
                  //onChange={e => setconfidence(e.target.value)}
              />
              <label htmlFor="interest_rate">Predicted Interest Rate</label>
              <input
                  disabled={true}
                  id="interest_rate"
                  type="interest_rate"
                  name="interest_rate"
                  value={interest_rate}
                  //onChange={e => setinterest_rate(e.target.value)}
              />

                  <div style={{ marginTop: '30px' }}>
                      <input
                          style={{ marginLeft: '12px' }}
                          className="muted-button"
                          type="button"
                          value="Cancel"
                          onClick={() => navigate('/showData')}
                      />


                  </div>
          </div>
      )


  }
  return (
    <div>
        <form onSubmit={handleFetch}>
        <label htmlFor="id">Enter User ID</label>
                <input
                    id="id"
                    type="number"
                    name="id"
                    ref={textInput}
                    value={id}
                    onChange={e => setId(e.target.value)}
                />
                <input type="submit" value="Submit" />
        </form>
    </div>
  )
}

export default ShowCase;
