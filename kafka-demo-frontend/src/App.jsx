import React, { useState } from "react";
import { registerSync, registerAsync } from "./api";
import "./App.css";

function App() {
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const [mode, setMode] = useState("");
  const [logs, setLogs] = useState([]);

  const addLog = (text) => {
    setLogs((prev) => [
      ...prev,
      `[${new Date().toLocaleTimeString()}] ${text}`,
    ]);
  };

  const handleRegister = async (type) => {
    setLoading(true);
    setMessage("");
    setMode(type);
    setLogs([]);

    const start = performance.now();
    try {
      addLog("[Auth Service] Nhận yêu cầu đăng ký người dùng...");

      const user = { email, name, password };

      if (type === "sync") {
        addLog("[Auth Service] Gọi trực tiếp sang Email Service...");
        await registerSync(user);
        addLog("[Email Service] Gửi mail thành công ✅");
      } else {
        addLog("[Auth Service] Publish message tới topic 'user-registered'...");
        await registerAsync(user);
        addLog("[Auth Service] Trả response ngay (không chờ Email Service)");
        setTimeout(() => {
          addLog("[Email Service] Nhận message từ Kafka và gửi mail ✅");
        }, 2000);
      }

      const end = performance.now();
      const time = ((end - start) / 1000).toFixed(2);

      setMessage(
        `✅ ${
          type === "sync" ? "Đăng ký (Đồng bộ)" : "Đăng ký (Bất đồng bộ)"
        } hoàn thành sau ${time}s`
      );
      addLog(`[Client] Hoàn tất sau ${time}s`);
    } catch (err) {
      setMessage("❌ Lỗi khi gửi request!");
      addLog("[Error] Không thể gửi request tới backend!");
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

      <div className="logs">
        <h3>📜 Log xử lý:</h3>
        <div className="log-box">
          {logs.map((log, index) => (
            <div key={index} className="log-line">
              {log}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default App;
