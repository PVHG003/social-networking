import authApi from "@/api/authApi";
import CompleteProfileForm from "@/components/CompleteProfileForm";
import { useNavigate } from "react-router-dom";

const CompleteProfilePage = () => {
  const navigate = useNavigate();

  const handleCompleteProfile = async (data: FormData) => {
    console.log("Complete Profile:", data);
    const response = await authApi.completeProfile(data);
    if (response.success) {
      console.log("Profile updated successfully");
      navigate("/profile");
    }
  };

  return <CompleteProfileForm onSubmit={handleCompleteProfile} />;
};

export default CompleteProfilePage;
