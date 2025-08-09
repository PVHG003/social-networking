import authApi from "@/api/authApi";
import AuthForm from "@/components/AuthForm";
import { useAuth } from "@/context/AuthContext";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
  const navigate = useNavigate();
  const { login } = useAuth();

  const handleLogin = async (data: { email: string; password: string }) => {
    const response = await authApi.login(data);
    if (response.success === true) {
      console.log("Login successful with token:", response.data.accessToken);
      const { accessToken, user } = response.data;
      login(user, accessToken);
      navigate("/");
    }
  };

  return <AuthForm type="login" onSubmit={handleLogin} />;
};

export default LoginPage;
