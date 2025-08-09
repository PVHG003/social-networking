import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { NavLink, Outlet, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import profileApi from "@/api/profileApi";

interface UserProfile {
  id: string;
  displayName: string;
  handleName: string;
  bio?: string;
  profileImage?: string;
}

const ProfileLayout = () => {
  const { handleName } = useParams();
  const [user, setUser] = useState<UserProfile | null>(null);

  const fetchUserProfile = async () => {
    if (!handleName) return;
    try {
      const response = await profileApi.getProfile(handleName);
      if (!response.success) throw new Error("Failed to fetch user profile");
      const data: UserProfile = response.data;
      setUser(data);
    } catch (error) {
      console.error("Error fetching user profile:", error);
    }
  };

  useEffect(() => {
    fetchUserProfile();
  }, []);

  return (
    <div className="max-w-2xl mx-auto p-4">
      {/* Header */}
      <div className="flex items-center gap-4">
        <Avatar className="w-20 h-20">
          <AvatarImage
            src={`http://localhost:8080/${user?.profileImage}`}
            alt={user?.displayName}
          />
          <AvatarFallback>{user?.displayName}</AvatarFallback>
        </Avatar>
        <div>
          <div className="text-xl font-bold">{user?.displayName}</div>
          <div className="text-gray-500">@{user?.handleName}</div>
          <div className="mt-2">{user?.bio || ""}</div>
        </div>
      </div>

      {/* Tabs */}
      <div className="flex gap-6 mt-6 border-b border-gray-300">
        <NavLink to="posts">Posts</NavLink>
        <NavLink to="followers">Followers</NavLink>
        <NavLink to="followings">Followings</NavLink>
      </div>

      {/* Tab Content */}
      <div className="mt-4">
        <Outlet />
      </div>
    </div>
  );
};

export default ProfileLayout;
