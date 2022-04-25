import React, { useState, useRef, useEffect } from 'react'
import Swal from 'sweetalert2';
function Add({ users, setUsers, setIsAdding }) {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [salary, setSalary] = useState('');
    const [date, setDate] = useState('');
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
    const handleAdd = e => {
        e.preventDefault();
        if (!firstName || !lastName || !email || !salary || !date) {
            return Swal.fire({
                icon: 'error',
                title: 'Error!',
                text: 'All fields are required.',
                showConfirmButton: true
            });
        }

        const id = users.length + 1;
        const newEmployee = {
            id,
            firstName,
            lastName,
            email,
            salary,
            date,
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
        users.push(newEmployee);
        setUsers(users);
        setIsAdding(false);

        Swal.fire({
            icon: 'success',
            title: 'Added!',
            text: `${firstName} ${lastName}'s data has been Added.`,
            showConfirmButton: false,
            timer: 1500
        });
    }
    return (
        <div className="small-container">
            <form onSubmit={handleAdd}>
                <h1>Add User</h1>
                <label htmlFor="firstName">First Name</label>
                <input
                    id="firstName"
                    type="text"
                    ref={textInput}
                    name="firstName"
                    value={firstName}
                    onChange={e => setFirstName(e.target.value)}
                />
                <label htmlFor="lastName">Last Name</label>
                <input
                    id="lastName"
                    type="text"
                    name="lastName"
                    value={lastName}
                    onChange={e => setLastName(e.target.value)}
                />
                <label htmlFor="email">Email</label>
                <input
                    id="email"
                    type="email"
                    name="email"
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                />
                <label htmlFor="salary">Salary ($)</label>
                <input
                    id="salary"
                    type="number"
                    name="salary"
                    value={salary}
                    onChange={e => setSalary(e.target.value)}
                />
                <label htmlFor="date">Date</label>
                <input
                    id="date"
                    type="date"
                    name="date"
                    value={date}
                    onChange={e => setDate(e.target.value)}
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
                    value={revol}
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
                    <input type="submit" value="Add" />
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
