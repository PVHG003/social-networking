import AuthForm from "@/components/AuthForm";

const LoginPage = () => {
  const handleLogin = (data: { email: string; password: string }) => {
    console.log("Login:", data);
    // Add your login logic here (e.g., API call)
  };

  return <AuthForm type="login" onSubmit={handleLogin} />;
};

export default LoginPage;
