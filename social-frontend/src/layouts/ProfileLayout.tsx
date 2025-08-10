import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import type { FunctionComponent } from "react";
import { Link, Outlet, useParams } from "react-router-dom";

interface ProfileLayoutProps {}

const ProfileLayout: FunctionComponent<ProfileLayoutProps> = () => {
  const { username } = useParams();

  return (
    <div>
      {/* Cover Image */}
      <div className="relative w-full h-48">
        <img
          src="https://placehold.co/1500x500"
          alt="cover"
          className="w-full h-full object-cover"
        />
        {/* Avatar positioned over cover */}
        <div className="absolute -bottom-12 left-6">
          <Avatar className="w-24 h-24 border-4 border-white">
            <AvatarImage src="" alt="avatar" />
            <AvatarFallback>{username?.charAt(0).toUpperCase()}</AvatarFallback>
          </Avatar>
        </div>
      </div>

      {/* Navigation Links */}
      <div className="mt-16 flex gap-4 px-6">
        <Link to={`/profile/${username}`}>Posts</Link>
        <Link to={`/profile/${username}/followers`}>Followers</Link>
        <Link to={`/profile/${username}/followings`}>Followings</Link>
      </div>

      {/* Outlet for nested routes */}
      <div className="px-6 py-4">
        <Outlet />
      </div>
    </div>
  );
};

export default ProfileLayout;
