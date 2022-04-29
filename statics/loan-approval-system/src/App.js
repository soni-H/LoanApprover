import './App.css';
import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Dashboard from './Components/Dashboard'
import Login from './Components/Login'
import SaveData from './Components/SaveHistoricalData';
import ShowData from './Components/ShowLoanCase';
function App() {
  return (
  <BrowserRouter>
    <Routes>
      <Route exact path="/" element={<Login />} />
     <Route path="/dashboard" element={<Dashboard />} />
     <Route path="/saveData" element={<SaveData />} />
     <Route path="/showData" element={<ShowData />} />
     <Route path="*" element={<h1>Error 404 Page not found !!</h1>} />
    </Routes>
  </BrowserRouter>
    
  );
}

export default App;
