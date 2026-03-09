import { Form, ListGroup, Button } from "react-bootstrap";
import { useAuth } from "../context/AuthContext";
import { useState } from "react";

const Sidebar = ({ users, onSelectUser, selectedUser, loggedUser }) => {
  const { logout } = useAuth();
  const [searchTerm, setSearchTerm] = useState("");
  const filteredUsers = users.filter((user) =>
    user.userName.toLowerCase().includes(searchTerm.toLowerCase()),
  );

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
          display: "flex",
          alignItems: "center",
          gap: "10px",
        }}
      >
        <div
          style={{
            width: "35px",
            height: "35px",
            borderRadius: "50%",
            background: "#25D366",
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            fontWeight: "bold",
          }}
        >
          {loggedUser?.userName?.charAt(0).toUpperCase()}
        </div>

        {loggedUser?.userName}
      </div>

      <div className="p-3">
        <Form.Control
          placeholder="Search contacts..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <div style={{ flex: 1, overflowY: "auto" }}>
        <ListGroup variant="flush">
          {filteredUsers.map((user) => (
            <ListGroup.Item
              key={user.userId}
              active={selectedUser?.userId === user.userId}
              style={{ cursor: "pointer" }}
              onClick={() => onSelectUser(user)}
            >
              {user.userName}
            </ListGroup.Item>
          ))}
        </ListGroup>
      </div>

      <div style={{ padding: "10px", borderTop: "1px solid #ccc" }}>
        <Button variant="danger" className="w-100" onClick={logout}>
          Logout
        </Button>
      </div>
    </div>
  );
};

export default Sidebar;
