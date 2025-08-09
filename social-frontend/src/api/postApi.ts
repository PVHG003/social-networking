import api from "./api";

const postApi = {
  getPostById: async (postId: string) => {
    const response = await api.get(`/posts/${postId}`);
    return response.data;
  },
  getPostLikes: async (postId: string) => {
    const response = await api.get(`/posts/${postId}/likes`);
    return response.data;
  },
  getPostComments: async (
    postId: string,
    page: number = 1,
    size: number = 10
  ) => {
    const response = await api.get(
      `/posts/${postId}/comments?page=${page}&size=${size}`
    );
    return response.data;
  },
};

export default postApi;
