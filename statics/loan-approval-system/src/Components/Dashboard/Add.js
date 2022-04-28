import React, { useState, useRef, useEffect } from 'react'
import Swal from 'sweetalert2';
import axios from 'axios'
function Add({ users, setUsers, setIsAdding }) {
    const [salary, setSalary] = useState('');
    const [loan_amnt, setloan_amnt] = useState('');
    const [term, setterm] = useState('');
    const [empl_length, setemp_length] = useState('');
    const [home_ownersh, sethome_ownersh] = useState('');
    const [purpose, setpurpose] = useState('');
    const [city, setcity] = useState('');
    const [dti, setdti] = useState('');
    const [delinq, setdelinq] = useState('');
    const [revol, setrevol] = useState('');
    const [totalacc, settotalacc] = useState('');
    const [prevcreditlength, setprevcreditlength] = useState('');
    const textInput = useRef(null);
    useEffect(() => {
        textInput.current.focus();
    }, [])
<<<<<<< HEAD
    const handlePredict = e => {
        e.preventDefault();
        const id = users.length + 1;
        const newUser = {
            annual_inc:salary,
            loan_amt:loan_amnt, 
            term:term, 
            emp_length:empl_length, 
            home_ownership:home_ownersh,
            purpose:purpose, 
            addr_state:city, 
            dti:dti, 
            delinq_2yrs:delinq, 
            revol_util:revol, 
            total_acc:totalacc, 
            longestprevcreditLength:prevcreditlength,
            verification_status:"verified"
        }
        console.log(newUser)
        const result=axios.get("http://app:8081/predictLoan",newUser);
        console.log(result);
=======
    const handlePredict = async(e) => {
        e.preventDefault();
        const id = users.length + 1;
        const newUser = {
            'annual_inc':salary,
            'loan_amt':loan_amnt, 
            'term':term, 
            'emp_length':empl_length, 
            'home_ownership':home_ownersh,
            'purpose':purpose, 
            'addr_state':city, 
            'dti':dti, 
            'delinq_2yrs':delinq, 
            'revol_util':revol, 
            'total_acc':totalacc, 
            'longestprevcreditLength':prevcreditlength,
            'verification_status':"verified"
        }
        const result=await axios.get("http://localhost:8081/predictLoan",{
            params: {
                foo: newUser
            }
        });
        console.log(newUser);
>>>>>>> 32694d2f4232ecd8276d4cabd892a6374268b758
        

        Swal.fire({
            icon: "success",
            title:"Results",
            text: `${result}`, 
            showConfirmButton: false,
            timer:1500

        })
    }
<<<<<<< HEAD
    const handleSave=() => {
        const id = users.length + 1;
        const newUser = {
=======
    const handleSave=async(e) => {
        const id = users.length + 1;
        const newUser = {
            id,
>>>>>>> 32694d2f4232ecd8276d4cabd892a6374268b758
            salary,
            loan_amnt, 
            term, 
            empl_length, 
            home_ownersh,
            purpose, 
            city, 
            dti, 
            delinq, 
            revol, 
            totalacc, 
            prevcreditlength,
        }
<<<<<<< HEAD
        console.log(newUser);
        axios.post("http://app:8081/saveCase",newUser);
=======
        await axios.post("http://localhost:8081/saveCase",newUser);
>>>>>>> 32694d2f4232ecd8276d4cabd892a6374268b758
        setUsers(users);
        setIsAdding(false);
        Swal.fire({
            icon: "success",
            title:"Results",
            text: `ADDED`, 
            showConfirmButton: false,
            timer:1500

        })

    }
    return (
        <div className="small-container">
            <form onSubmit={handlePredict}>
                <h1>Predict Case</h1>
                <label htmlFor="salary">Salary ($)</label>
                <input
                    id="salary"
                    type="number"
                    name="salary"
                    ref={textInput}
                    value={salary}
                    onChange={e => setSalary(e.target.value)}
                />
                <label htmlFor="loan_amnt">Loan amount</label>
                <input
                    id="loan_amnt"
                    type="loan_amnt"
                    name="loan_amnt"
                    value={loan_amnt}
                    onChange={e => setloan_amnt(e.target.value)}
                />
                <label htmlFor="term">Term</label>
                <input
                    id="term"
                    type="term"
                    name="term"
                    value={term}
                    onChange={e => setterm(e.target.value)}
                />
                <label htmlFor="empl_length">Employee Length</label>
                <input
                    id="empl_length"
                    type="empl_length"
                    name="empl_length"
                    value={empl_length}
                    onChange={e => setemp_length(e.target.value)}
                />
                <label htmlFor="home_ownersh">Housing status</label>
                <input
                    id="home_ownersh"
                    type="home_ownersh"
                    name="home_ownersh"
                    value={home_ownersh}
                    onChange={e => sethome_ownersh(e.target.value)}
                />
                <label htmlFor="purpose">Purpose</label>
                <input
                    id="purpose"
                    type="purpose"
                    name="purpose"
                    value={purpose}
                    onChange={e => setpurpose(e.target.value)}
                />
                <label htmlFor="city">City</label>
                <input
                    id="city"
                    type="city"
                    name="city"
                    value={city}
                    onChange={e => setcity(e.target.value)}
                />
                <label htmlFor="dti">DTI</label>
                <input
                    id="dti"
                    type="dti"
                    name="dti"
                    value={dti}
                    onChange={e => setdti(e.target.value)}
                />
                <label htmlFor="delinq">delinq_2yrs</label>
                <input
                    id="delinq"
                    type="delinq"
                    name="delinq"
                    value={delinq}
                    onChange={e => setdelinq(e.target.value)}
                />
                <label htmlFor="revol">Revol_util</label>
                <input
                    id="revol"
                    type="revol"
                    name="revol"
                    value={revol}
                    onChange={e => setrevol(e.target.value)}
                />
                <label htmlFor="totalacc">Total accounts</label>
                <input
                    id="totalacc"
                    type="totalacc"
                    name="totalacc"
                    value={totalacc}
                    onChange={e => settotalacc(e.target.value)}
                />
                <label htmlFor="prevcreditlength">Age of oldest active account</label>
                <input
                    id="prevcreditlength"
                    type="prevcreditlength"
                    name="prevcreditlength"
                    value={prevcreditlength}
                    onChange={e => setprevcreditlength(e.target.value)}
                />
                <div style={{ marginTop: '30px' }}>
                    <input type="submit" value="Predict" />
                    <input
                        style={{ marginLeft: '12px' }}
                        className="muted-button"
                        type="button"
                        value="Save"
                        onClick={handleSave}
                    />
                    <input
                        style={{ marginLeft: '12px' }}
                        className="muted-button"
                        type="button"
                        value="Cancel"
                        onClick={() => setIsAdding(false)}
                    />
                    
                </div>
                
            </form>
        </div>
    )
}

export default Add
