import PostList from "@/components/post/PostList";
import type { FunctionComponent } from "react";
import { useParams } from "react-router-dom";

interface ProfilePageProps {}

const ProfilePage: FunctionComponent<ProfilePageProps> = () => {
  const { username } = useParams();
  return (
    <div className="flex flex-col items-center justify-center h-full">
      <h1 className="text-3xl font-bold mb-4">
        Welcome to your Profile Page, {username ?? "Guest"}
      </h1>
      <PostList />
    </div>
  );
};

export default ProfilePage;
