import React from 'react'

function List({ users, handleEdit }) {
    const formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD',
        minimumFractionDigits: null
    });



    return (
        <div className='contain-table'>

            <table className='striped-table'>
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Emails</th>
                        <th>Salary</th>
                        <th>Date</th>
                        <th>Loan amount</th>
                        <th>Term</th>
                        <th>Employee Length</th>
                        <th>Housing Status</th>
                        <th>Purpose</th>
                        <th>City</th>
                        <th>DTI</th>
                        <th>Delinq_2yrs</th>
                        <th>Revol_util</th>
                        <th>Total accounts</th>

                        <th>Age of oldest active account</th>
                        <th colSpan={2} className="text-center">
                            Actions
                        </th>
                    </tr>
                </thead>
                <tbody>
                    {users.length > 0 ? (
                        users.map((user, i) => (
                            <tr key={user.id}>
                                <td>{i + 1}</td>
                                <td>{user.firstName}</td>
                                <td>{user.lastName}</td>
                                <td>{user.email}</td>
                                <td>{formatter.format(user.salary)}</td>
                                <td>{user.date} </td>

                                <td>{user.loan_amnt} </td>
                                <td>{user.term} </td>
                                <td>{user.empl_length} </td>
                                <td>{user.home_ownersh}</td>
                                <td>{user.purpose} </td>
                                <td>{user.city} </td>
                                <td>{user.dti}</td>
                                <td>{user.delinq}</td>
                                <td>{user.revil}</td>
                                <td>{user.totalacc}</td>
                                <td>{user.creditlength}</td>
                                <td className="text-right">
                                    <button
                                        onClick={() => handleEdit(user.id)}
                                        className="button muted-button"
                                    >
                                        Edit
                                    </button>
                                </td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan={7}>No Employees</td>
                        </tr>
                    )}
                </tbody>
            </table>

        </div>
    )
}

export default List
