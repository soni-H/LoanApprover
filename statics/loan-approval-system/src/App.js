import './App.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Dashboard from './Components/Dashboard'
import Login from './Components/Login'
 
function App() {
  return (
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<Login />} />
     <Route path="/dashboard" element={<Dashboard auth={false}/>} />
     <Route path="*" element={<h1>Error 404 Page not found !!</h1>} />
    </Routes>
  </BrowserRouter>
    
  );
}

export default App;
