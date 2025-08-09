import AuthForm from "@/components/auth/AuthForm";
import type { FunctionComponent } from "react";

interface LoginPageProps {}

const RegisterPage: FunctionComponent<LoginPageProps> = () => {
  return <AuthForm type={"register"} />;
};

export default RegisterPage;
