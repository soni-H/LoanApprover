import React from 'react'
import { useNavigate } from 'react-router-dom'

function Header({ setIsAdding }) {
  const navigate = useNavigate()
  const handleLogout = () => {
    console.log("Logout Clicked");
    navigate('/')
  }
  const handleRecord = () => {
    setIsAdding(false);
    navigate("/saveData")
  }
  const handleCase = () => {
    setIsAdding(false);
    navigate("/showData")
  }
  return (
    <div>
      <header className="header">
        <div className="title"><h1>Users Dashboard</h1></div>
        <div className="logout"><button variant='contained' color='warning' size='large' className="round-button logout" onClick={handleLogout} sx={{ mt: 8 }}>Logout</button>
</div>
      </header>
      <div className="grid-container">
        <h2>Services</h2>
        <button variant='contained' color='warning' size='large' onClick={() => setIsAdding(true)} className='round-button'>Predict Case</button>
        <button variant='contained' color='warning' size='large' className="round-button" onClick={handleRecord} sx={{ mt: 8 }}>Save Record</button>
        <button variant='contained' color='warning' size='large' className="round-button" onClick={handleCase} sx={{ mt: 8 }}>Show Record</button>
      </div>
    </div>

  )
}

export default Header
