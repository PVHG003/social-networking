import api from "./api";

const profileApi = {
  getProfile: async (handleName: string) => {
    const response = await api.get(`/users/${handleName}`);
    return response.data;
  },
  getUserPosts: async (handleName: string) => {
    const response = await api.get(`/users/${handleName}/posts`);
    return response.data;
  },
  getUserFollowers: async (handleName: string) => {
    const response = await api.get(`/users/${handleName}/followers`);
    return response.data;
  },
  getUserFollowings: async (handleName: string) => {
    const response = await api.get(`/users/${handleName}/followings`);
    return response.data;
  },
};

export default profileApi;
