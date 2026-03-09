import { useEffect, useState } from "react";
import Sidebar from "../components/Sidebar";
import ChatWindow from "../components/ChatWindow";
import { useAuth } from "../context/AuthContext";
import { getAllUsers } from "../services/userService";
import { getConversation } from "../services/messageservice";

const Dashboard = () => {
  const { user } = useAuth();

  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState(null);
  const [messages, setMessages] = useState([]);
  const [loggedUser, setLoggedUser] = useState(null);

  useEffect(() => {
    const fetchUsers = async () => {
      const res = await getAllUsers();

      // Find logged-in user's full details
      const loggedUserData = res.data.Users.find((u) => u.email === user.email);

      // Remove logged user from sidebar list
      const filteredUsers = res.data.Users.filter(
        (u) => u.email !== user.email,
      );

      setLoggedUser(loggedUserData);
      setUsers(filteredUsers);
    };

    if (user) {
      fetchUsers();
    }
  }, [user]);

  useEffect(() => {
    if (!selectedUser) return;

    const fetchMessages = async () => {
      const data = await getConversation(selectedUser.userId);
      setMessages(data);
    };

    fetchMessages();
  }, [selectedUser]);

  return (
    <div style={{ height: "100vh", overflow: "hidden" }}>
      <div style={{ display: "flex", height: "100%" }}>
        <div style={{ width: "300px", borderRight: "1px solid #ccc" }}>
          <Sidebar
            users={users}
            selectedUser={selectedUser}
            onSelectUser={setSelectedUser}
            loggedUser={loggedUser}
          />
        </div>

        <div style={{ flex: 1 }}>
          <ChatWindow
            selectedUser={selectedUser}
            messages={messages}
            setMessages={setMessages}
          />
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
