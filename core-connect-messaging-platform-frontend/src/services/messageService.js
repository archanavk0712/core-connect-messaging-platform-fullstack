import api from "./api";

export const getConversation = async (contactId) => {
  const res = await api.get(`/messages/${contactId}`);
  return res.data.data; // because backend returns { error:false, data:[...] }
};

export const sendMessage = async (messageData) => {
  const res = await api.post("/messages/send", messageData);
  return res.data;
};
