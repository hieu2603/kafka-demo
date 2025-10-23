import React, { useState } from "react";
import { registerSync, registerAsync } from "./api";
import "./App.css";

function App() {
  const [email, setEmail] = useState("demo@example.com");
  const [name, setName] = useState("Demo User");
  const [password, setPassword] = useState("123456");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const [mode, setMode] = useState("");

  const handleRegister = async (type) => {
    setLoading(true);
    setMessage("");
    setMode(type);

    const start = performance.now();
    try {
      const user = { email, name, password };

      if (type === "sync") {
        await registerSync(user);
      } else {
        await registerAsync(user);
      }

      const end = performance.now();
      const time = ((end - start) / 1000).toFixed(2);

      setMessage(
        `✅ ${
          type === "sync" ? "Đăng ký (Đồng bộ)" : "Đăng ký (Bất đồng bộ)"
        } hoàn thành sau ${time}s`
      );
    } catch (err) {
      setMessage("❌ Lỗi khi gửi request!");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="App">
      <h1>Demo Apache Kafka với Spring Boot</h1>
      <p>So sánh xử lý Đồng bộ và Bất đồng bộ</p>

      <div className="form">
        <input
          type="text"
          placeholder="Tên người dùng"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <input
          type="email"
          placeholder="Email người dùng"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="Mật khẩu"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <div className="buttons">
          <button onClick={() => handleRegister("sync")} disabled={loading}>
            {loading && mode === "sync" ? "Đang gửi..." : "Đăng ký (Đồng bộ)"}
          </button>

          <button onClick={() => handleRegister("async")} disabled={loading}>
            {loading && mode === "async"
              ? "Đang gửi..."
              : "Đăng ký (Bất đồng bộ)"}
          </button>
        </div>
      </div>

      {message && <div className="message">{message}</div>}
    </div>
  );
}

export default App;
