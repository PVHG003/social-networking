import { Route, Routes } from "react-router-dom";

import AuthLayout from "@/layouts/AuthLayout";

import LoginPage from "@/pages/auth/LoginPage";
import RegisterPage from "@/pages/auth/RegisterPage";
import MainLayout from "@/layouts/MainLayout";
import HomePage from "@/pages/home/HomePage";
import FeedPage from "@/pages/home/FeedPage";
import ProfilePage from "@/pages/profile/ProfilePage";
import ProfileLayout from "@/layouts/ProfileLayout";

export default function AppRoutes() {
  const authenticated = true; // Replace with context/auth check

  return (
    <Routes>
      {/* Public Routes */}
      <Route element={<AuthLayout />}>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
      </Route>
      <Route element={<MainLayout />}>
        <Route index element={<HomePage />} />
        <Route path="/feed" element={<FeedPage />} />

        {/* <Route path="/profile/me" element={<ProfilePage />} /> */}
        {/* <Route path="/profile/update" element={<ProfileUpdatePage />} /> */}

        {/* <Route path="/auth/update-profile" element={<UpdateProfilePage />} /> */}

        {/* <Route path="/chat" element={<ChatPage />} /> */}
        {/* <Route path="/chat/:conversationId" element={<ConversationPage />} /> */}
        <Route path="/profile/:username" element={<ProfileLayout />}>
          <Route index element={<ProfilePage />} />
          {/* <Route path="/followers" element={<FollowersPage />} /> */}
          {/* <Route path="/following" element={<FollowingPage />} /> */}
        </Route>
      </Route>

      {/* <Route path="*" element={<NotFoundPage />} />  */}
    </Routes>
  );
}
