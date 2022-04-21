import React from 'react'
import { useNavigate } from 'react-router-dom'

function Header({ setIsAdding }) {
  const navigate = useNavigate()
  const handleLogout = () => {
    console.log("Logout Clicked");
    navigate('/')
  }
  return (
    <header>
        <h1>Users Dashboard</h1>
        <div style={{ marginTop: '30px', marginBottom: '18px' }}>
        <button onClick={() => setIsAdding(true)} className='round-button'>Add User</button>
        <div style={{float:'right'}}>
        <button variant='contained' color='warning' size='large' className="round-button"  onClick={handleLogout} sx={{ mt: 8 }}>Logout</button>
        </div>
        </div>
    </header>
  )
}

export default Header
