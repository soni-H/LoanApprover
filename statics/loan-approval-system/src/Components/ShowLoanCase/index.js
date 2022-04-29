import React, { useState, useRef, useEffect } from 'react'

function ShowCase() {
  const [id, setId] = useState('');

  const textInput = useRef(null);
  
  const handleFetch = () => {

  }
  return (
    <div>
        <form onSubmit={handleFetch}>
        <label htmlFor="id">Enter User ID</label>
                <input
                    id="id"
                    type="number"
                    name="id"
                    ref={textInput}
                    value={id}
                    onChange={e => setId(e.target.value)}
                />
                <input type="submit" value="Predict" />
        </form>
    </div>
  )
}

export default ShowCase;
