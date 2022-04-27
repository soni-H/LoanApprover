import React, { useState } from 'react';
import { userData } from '../../data'
import Header from './Header';
import Add from './Add';

function Dashboard() {


    const [users, setUsers] = useState(userData);
    const [isAdding, setIsAdding] = useState(false);
    return (

        <div className="container">
            {!isAdding && (
                <>
                    <Header setIsAdding={setIsAdding} />
                </>
            )}
            {/* Add */}
            {isAdding && (
                <Add
                    users={users}
                    setUsers={setUsers}
                    setIsAdding={setIsAdding}
                />
            )}


        </div>

    )

}
export default Dashboard;
