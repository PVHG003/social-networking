import authApi from "@/api/authApi";
import AuthForm from "@/components/AuthForm";
import { useNavigate } from "react-router-dom";

const RegisterPage = () => {
  const navigate = useNavigate();

  const handleRegister = async (data: {
    email: string;
    password: string;
    confirmPassword?: string;
  }) => {
    const response = await authApi.register(data);
    if (response.success === true) {
      localStorage.setItem("token", response.data.accessToken);
      navigate("/complete-profile");
    }
  };

  return <AuthForm type="register" onSubmit={handleRegister} />;
};

export default RegisterPage;
