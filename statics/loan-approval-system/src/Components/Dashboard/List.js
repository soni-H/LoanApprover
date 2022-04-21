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
                        <th>Loan Status</th>
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
                                <td>{user.loan_status}</td>
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
