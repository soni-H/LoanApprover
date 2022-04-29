import React, { useState, useRef, useEffect } from 'react'
import Swal from 'sweetalert2';
import axios from 'axios'
import Select from 'react-select'
import { NavLink, useNavigate } from 'react-router-dom';
function Add({ users, setUsers, setIsAdding }) {
    const navigate = useNavigate();

    const [salary, setSalary] = useState('');
    const [loan_amnt, setloan_amnt] = useState('');
    const [term, setterm] = useState(['36 months', '60 months']);
    const [empl_length, setemp_length] = useState('');
    const [home_ownersh, sethome_ownersh] = useState(['RENT', 'OWN', 'MORTGAGE', 'OTHER', 'NONE', 'ANY']);
    const [purpose, setpurpose] = useState(['credit_card', 'car', 'small_business', 'other', 'wedding',
        'debt_consolidation', 'home_improvement', 'major_purchase',
        'medical', 'moving', 'vacation', 'house', 'renewable_energy',
        'educational']);
    const [city, setcity] = useState(['AZ', 'GA', 'IL', 'CA', 'TX', 'VA', 'MO', 'CT', 'UT', 'FL', 'NY',
        'PA', 'MN', 'NJ', 'OR', 'KY', 'OH', 'SC', 'RI', 'LA', 'MA', 'WA',
        'WI', 'AL', 'NV', 'CO', 'MD', 'WV', 'VT', 'MI', 'DC', 'SD', 'NC',
        'AR', 'KS', 'NM', 'HI', 'AK', 'OK', 'MT', 'WY', 'NH', 'DE', 'MS',
        'TN', 'IA', 'NE', 'ID', 'IN', 'ME']);
    const [dti, setdti] = useState('');
    const [delinq, setdelinq] = useState('');
    const [revol, setrevol] = useState('');
    const [totalacc, settotalacc] = useState('');
    const [prevcreditlength, setprevcreditlength] = useState('');
    const [verificationStatus, setverificationStatus] = useState(['verified', 'not verified'])

    const [visible, setVisible] = React.useState(false);
    const [confidence, setconfidence] = useState('');
    const [loanverified, setloanverified] = useState('');
    const [interest_rate, setinterest_rate] = useState('');
    const textInput = useRef(null);

    const home_ownership_options = [
        { value: 'MORTGAGE', label: 'MORTGAGE' },
        { value: 'RENT', label: 'RENT' },
        { value: 'OTHER', label: 'OTHER' },
        { value: 'NONE', label: 'NONE' },
        { value: 'ANY', label: 'ANY' },
        { value: 'OWN', label: 'OWN' }
    ]

    const purpose_options = [
        { value: 'credit_card', label: 'credit_card' },
        { value: 'car', label: 'car' },
        { value: 'small_business', label: 'small_business' },
        { value: 'other', label: 'other' },
        { value: 'wedding', label: 'wedding' },
        { value: 'debt_consolidation', label: 'debt_consolidation' },
        { value: 'home_improvement', label: 'home_improvement' },
        { value: 'major_purchase', label: 'major_purchase' },
        { value: 'medical', label: 'medical' },
        { value: 'moving', label: 'moving' },
        { value: 'vacation', label: 'vacation' },
        { value: 'house', label: 'house' },
        { value: 'renewable_energy', label: 'renewable_energy' },
        { value: 'educational', label: 'educational' }
    ]
    const term_options = [
        { value: '36 months', label: '36 months' },
        { value: '60 months', label: '60 months' }
    ]
    const verification_status_options = [
        { value: 'not verified', label: 'not verified' },
        { value: 'verified', label: 'verified' },
    ]

    const addr_state_options = [
        { value: 'AZ', label: 'AZ' },
        { value: 'GA', label: 'GA' },
        { value: 'IL', label: 'IL' },
        { value: 'CA', label: 'CA' },
        { value: 'TX', label: 'TX' },
        { value: 'VA', label: 'VA' },
        { value: 'MO', label: 'MO' },
        { value: 'CT', label: 'CT' },
        { value: 'UT', label: 'UT' },
        { value: 'FL', label: 'FL' },
        { value: 'NY', label: 'NY' },
        { value: 'PA', label: 'PA' },
        { value: 'MN', label: 'MN' },
        { value: 'NJ', label: 'NJ' },
        { value: 'OR', label: 'OR' },
        { value: 'KY', label: 'KY' },
        { value: 'OH', label: 'OH' },
        { value: 'SC', label: 'SC' },
        { value: 'RI', label: 'RI' },
        { value: 'LA', label: 'LA' },
        { value: 'MA', label: 'MA' },
        { value: 'WA', label: 'WA' },
        { value: 'WI', label: 'WI' },
        { value: 'AL', label: 'AL' },
        { value: 'NV', label: 'NV' },
        { value: 'CO', label: 'CO' },
        { value: 'MD', label: 'MD' },
        { value: 'WV', label: 'WV' },
        { value: 'VT', label: 'VT' },
        { value: 'MI', label: 'MI' },
        { value: 'DC', label: 'DC' },
        { value: 'SD', label: 'SD' }
    ]

    useEffect(() => {
        textInput.current.focus();
    }, [])
    const handlePredict = async (e) => {
        e.preventDefault();
        const id = users.length + 1;

        const result = await axios.get("http://localhost:8081/predictLoan", {
            params: {
                'annual_inc': salary,
                'loan_amnt': loan_amnt,
                'term': term['value'],
                'emp_length': empl_length,
                'home_ownership': home_ownersh['value'],
                'purpose': purpose['value'],
                'addr_state': city['value'],
                'dti': dti,
                'delinq_2yrs': delinq,
                'revol_util': revol,
                'total_acc': totalacc,
                'longest_credit_length': prevcreditlength,
                'verification_status': verificationStatus['value']
            }
        });
        let data = result['data']
        setconfidence(data['confidence']);
        setloanverified(data['prediction']);
        setinterest_rate(data['interest_rate']);
        if (result['status'] === 200) {

            setVisible(true);
        }



        console.log(confidence, loanverified, interest_rate);

        /*Swal.fire({
            icon: "success",
            title:"Results",
            text: `${result}`, 
            showConfirmButton: false
            //timer:15000

        })*/
    }
    const handleSave = async (e) => {
        const id = users.length + 1;
        if (confidence === '' || loanverified === '') {
            Swal.fire({
                icon: "error",
                title: "Results",
                text: `Kindly predict the case before submitting.`,
                showConfirmButton: true,
                //timer: 1500

            })
        } else {
            let params = {
                'annual_inc': salary,
                'loan_amnt': loan_amnt,
                'term': term['value'],
                'emp_length': empl_length,
                'home_ownership': home_ownersh['value'],
                'purpose': purpose['value'],
                'addr_state': city['value'],
                'dti': dti,
                'delinq_2yrs': delinq,
                'revol_util': revol,
                'total_acc': totalacc,
                'longest_credit_length': prevcreditlength,
                'verification_status': verificationStatus['value'],
                'confidence': confidence,
                'prediction': loanverified,
                'interest_rate': interest_rate
            }
            const result = await axios.post("http://localhost:8081/saveCase", params);
            if (result['data'] === -1) {
                Swal.fire({
                    icon: "error",
                    title: "Results",
                    text: `Error occurred while saving the record!`,
                    showConfirmButton: true,
                    //timer:1500

                })
            } else {
                Swal.fire({
                    icon: "success",
                    title: "Results",
                    text: `Case ID : ` + result['data'],
                    showConfirmButton: true,
                    //timer:1500

                })
            }
        }
        

    }
    const handleRecord = () => {
        setIsAdding(false);
        navigate("/saveData")
    }
    const handleCase= () => {
        setIsAdding(false);
        navigate("/showData")
    }

    return (
        <div className="small-container">
            <form onSubmit={handlePredict}>
                <h1>Predict Case</h1>
                <label htmlFor="salary">Annual income ($)</label>
                <input
                    id="salary"
                    type="number"
                    name="salary"
                    ref={textInput}
                    value={salary}
                    onChange={e => setSalary(e.target.value)}
                />
                <label htmlFor="loan_amnt">Requested loan amount ($)</label>
                <input
                    id="loan_amnt"
                    type="loan_amnt"
                    name="loan_amnt"
                    value={loan_amnt}
                    onChange={e => setloan_amnt(e.target.value)}
                />
                <label htmlFor="term">Loan term length (months)</label>
                <Select
                    options={term_options}
                    id="term"
                    type="term"
                    name="term"
                    value={term}
                    onChange={e => setterm(e)}
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

                <Select
                    options={home_ownership_options}
                    //id="home_ownersh"
                    //type="home_ownersh"
                    //name="home_ownersh"
                    value={home_ownersh}
                    onChange={e => sethome_ownersh(e)}
                />
                <label htmlFor="purpose">Purpose of loan</label>
                <Select
                    options={purpose_options}
                    id="purpose"
                    type="purpose"
                    name="purpose"
                    value={purpose}
                    onChange={e => setpurpose(e)}
                />
                <label htmlFor="city">State of residence</label>
                <Select
                    options={addr_state_options}
                    id="city"
                    type="city"
                    name="city"
                    value={city}
                    onChange={e => setcity(e)}
                />
                <label htmlFor="dti">Debt to income ratio</label>
                <input
                    id="dti"
                    type="dti"
                    name="dti"
                    value={dti}
                    onChange={e => setdti(e.target.value)}
                />
                <label htmlFor="delinq">Number of misdemeanor in the past 2 years</label>
                <input
                    id="delinq"
                    type="delinq"
                    name="delinq"
                    value={delinq}
                    onChange={e => setdelinq(e.target.value)}
                />
                <label htmlFor="revol">Revolving credit line utilized</label>
                <input
                    id="revol"
                    type="revol"
                    name="revol"
                    value={revol}
                    onChange={e => setrevol(e.target.value)}
                />
                <label htmlFor="totalacc">Total accounts (number of credit lines)</label>
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
                <label htmlFor="verificationStatus">Income verification status</label>
                <Select
                    options={verification_status_options}
                    id="verificationStatus"
                    type="verificationStatus"
                    name="verificationStatus"
                    value={verificationStatus}
                    onChange={e => setverificationStatus(e)}
                />
                <div style={{ display: visible ? 'block' : 'none' }}>
                    <label htmlFor="confidence">Confidence</label>
                    <input
                        disabled={true}
                        id="confidence"
                        type="confidence"
                        name="confidence"
                        value={confidence}
                    //onChange={e => setconfidence(e.target.value)}
                    />
                    <label htmlFor="loanverified">Loan Verification Status</label>
                    <input
                        disabled={true}
                        id="loanverified"
                        type="loanverified"
                        name="loanverified"
                        value={loanverified}
                    //onChange={e => setloanverified(e.target.value)}
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
                </div>
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
                    <input
                        style={{ marginLeft: '12px' }}
                        className="muted-button"
                        type="button"
                        value="Save Record"
                        onClick={handleRecord}
                    />
                    <input
                        style={{ marginLeft: '12px' }}
                        className="muted-button"
                        type="button"
                        value="Show Case"
                        onClick={handleCase}
                    />

                </div>

            </form>
        </div>
    )
}

export default Add
