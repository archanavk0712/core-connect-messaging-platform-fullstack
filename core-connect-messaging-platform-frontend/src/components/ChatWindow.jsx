import { useState, useEffect, useRef } from "react";
import { Form, Button } from "react-bootstrap";
import MessageBubble from "./MessageBubble";
import { useAuth } from "../context/AuthContext";
import { getConversation, sendMessage } from "../services/messageservice";

const ChatWindow = ({ selectedUser, messages, setMessages }) => {
  const { user } = useAuth();
  const [text, setText] = useState("");
  const messagesEndRef = useRef(null);

  //Auto scroll when messages update
  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  //Polling (auto refresh every 3 seconds)
  useEffect(() => {
    if (!selectedUser || !user) return;

    const interval = setInterval(async () => {
      try {
        const data = await getConversation(selectedUser.userId);
        setMessages(data);
      } catch (err) {
        console.error("Polling failed", err.response?.status);
      }
    }, 3000);

    return () => clearInterval(interval);
  }, [selectedUser, user, setMessages]);

  if (!selectedUser) {
    return (
      <div style={{ padding: "20px" }}>Select a user to start chatting</div>
    );
  }

  const handleSend = async (e) => {
    e.preventDefault();

    if (!text.trim()) return;

    const messageData = {
      receiverId: selectedUser.userId,
      content: text,
    };

    try {
      await sendMessage(messageData);

      const newMessage = {
        messageId: Date.now(),
        content: text,
        sender: { email: user.email },
        receiver: { email: selectedUser.email },
        timestamp: new Date().toISOString(),
      };

      setMessages((prev) => [...prev, newMessage]);
      setText("");
    } catch (err) {
      console.error("Message send failed");
    }
  };

  return (
    <div
      style={{
        height: "100%",
        display: "flex",
        flexDirection: "column",
      }}
    >
      <div
        style={{
          padding: "15px",
          background: "#075E54",
          color: "#fff",
          fontWeight: "bold",
        }}
      >
        {selectedUser.userName}
      </div>

      <div
        style={{
          flex: 1,
          overflowY: "auto",
          padding: "15px",
        }}
      >
        {messages.map((msg) => {
          const isSent = msg.sender.email === user.email;

          return (
            <MessageBubble
              key={msg.messageId}
              type={isSent ? "sent" : "received"}
              message={msg.content}
              timestamp={msg.timestamp}
            />
          );
        })}

        <div ref={messagesEndRef} />
      </div>

      <div
        style={{
          padding: "10px",
          borderTop: "1px solid #ccc",
          background: "#fff",
        }}
      >
        <Form className="d-flex" onSubmit={handleSend}>
          <Form.Control
            value={text}
            onChange={(e) => setText(e.target.value)}
            placeholder="Type a message..."
          />
          <Button
            type="submit"
            style={{
              marginLeft: "10px",
              background: "#25D366",
              border: "none",
            }}
          >
            Send
          </Button>
        </Form>
      </div>
    </div>
  );
};

export default ChatWindow;