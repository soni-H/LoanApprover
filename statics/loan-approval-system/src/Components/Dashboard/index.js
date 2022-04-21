import React, {useState} from 'react';
import {userData} from '../../data'
import Swal from 'sweetalert2';
import Header from './Header';
import Add from './Add';
import Edit from './Edit';
import List from './List';

function Dashboard(){
    const [users, setUsers] = useState(userData);
    const [selectedUser, setSelectedUser] = useState(null);
    const [isAdding, setIsAdding] = useState(false);
    const [isEditing, setIsEditing] = useState(false);
    
    const handleEdit = (id) => {
        const [user] = users.filter(user => user.id === id);

        setSelectedUser(user);
        setIsEditing(true);

    }
    return(

        <div className="container">
            {!isAdding && !isEditing &&  (
                <>
                  <Header setIsAdding={setIsAdding}/>
                  <List users={users} handleEdit={handleEdit}/>
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
            {isEditing && (
                <Edit
                 users={users}
                 selectedUser={selectedUser}
                 setUsers={setUsers}
                 setIsEditing={setIsEditing}
                /> 
                    
            )}


        </div>

    )
}
export default Dashboard;