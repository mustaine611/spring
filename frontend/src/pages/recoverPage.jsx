import { useState } from "react";
import { recover } from "../services/authService";

const RecoverPage = () => {
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await recover({ email });
      setMessage(response);
    } catch (error) {
      setMessage("Error recovering account");
    }
  };

  return (
    <div>
      <h1>Recover Account</h1>

      <form onSubmit={handleSubmit}>
        <input
          type="email"
          placeholder="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <button type="submit">Recover</button>
      </form>

      <p>{message}</p>
    </div>
  );
};

export default RecoverPage;