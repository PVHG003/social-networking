import profileApi from "@/api/profileApi";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

interface UserProfile {
  id: string;
  displayName: string;
  handleName: string;
  bio?: string;
  profileImage?: string;
}

const ProfilePage = () => {
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
    <div>
      <h1>Profile Page</h1>
      {/* Profile content goes here */}
    </div>
  );
};

export default ProfilePage;
