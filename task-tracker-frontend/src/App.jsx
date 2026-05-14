import { useState, useEffect } from 'react';
import api from './api/axiosConfig';

function App() {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    const fetchTasks = async () => {
      try {
        const response = await api.get('/tasks/user/2');
        setTasks(response.data);
      } catch (erros) {
        console.error("Terjadi error", error);
      }
    };

    fetchTasks();
  }, []);

  return (
    <div>
      <h1>Task tracker CLI</h1>
      <div> {tasks.length > 0 ? (
        tasks.map((task) => (
          <div key={tasks.id}>
          <div>
            <h3>{task.description}</h3>
            <p>ID: {task.id}</p>
          </div>
        </div>
        ))
      ) : (
        <p>No Info</p>
      )}
      </div>
    </div>
  );
}

export default App;