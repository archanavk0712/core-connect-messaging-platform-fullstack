const MessageBubble = ({ type, message, timestamp }) => {
  const isSent = type === "sent";

  const formatTime = (time) => {
    if (!time) return "";

    const date = new Date(time);
    return date.toLocaleTimeString([], {
      hour: "2-digit",
      minute: "2-digit",
    });
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: isSent ? "flex-end" : "flex-start",
        marginBottom: "10px",
      }}
    >
      <div
        style={{
          background: isSent ? "#DCF8C6" : "#fff",
          padding: "10px 15px",
          borderRadius: "10px",
          maxWidth: "60%",
          boxShadow: "0 1px 2px rgba(0,0,0,0.1)",
        }}
      >
        <div>{message}</div>

        <div
          style={{
            fontSize: "11px",
            color: "#666",
            textAlign: "right",
            marginTop: "5px",
          }}
        >
          {formatTime(timestamp)}
        </div>
      </div>
    </div>
  );
};

export default MessageBubble;
