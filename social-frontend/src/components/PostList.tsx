import { useParams } from "react-router-dom";
import PostCard from "./PostCard";
import { useEffect, useState } from "react";
import profileApi from "@/api/profileApi";

interface PostDetails {
  id: string;
  username: string;
  profilePicture: string | null;
  content: string | null;
  postMedias: string[] | null;
  likeCounts: number;
  commentCounts: number;
  createdAt: string;
  updatedAt: string;
}

const PostList = () => {
  const { handleName } = useParams();
  const [posts, setPosts] = useState<PostDetails[]>([]);

  const fetchPosts = async () => {
    if (!handleName) return;
    try {
      const response = await profileApi.getUserPosts(handleName);
      if (!response.success) throw new Error("Failed to fetch user posts");
      setPosts(response.data);
    } catch (error) {
      console.error("Error fetching user posts:", error);
    }
  };

  useEffect(() => {
    fetchPosts();
  });

  return (
    <div className="space-y-4">
      {posts.map((post) => (
        <PostCard key={post?.id} post={post} />
      ))}
    </div>
  );
};

export default PostList;
