import AuthForm from "@/components/AuthForm";

const RegisterPage = () => {
  const handleRegister= (data: { email: string; password: string, confirmPassword?: string }) => {
    console.log("Login:", data);
    // Add your login logic here (e.g., API call)
  };

  return <AuthForm type="register" onSubmit={handleRegister} />;
};

export default RegisterPage;
