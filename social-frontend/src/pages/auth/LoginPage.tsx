import AuthForm from "@/components/auth/AuthForm";
import type { FunctionComponent } from "react";

interface LoginPageProps {
    
}
 
const LoginPage: FunctionComponent<LoginPageProps> = () => {
    return (  
        <AuthForm type={'login'}/>
    );
}
 
export default LoginPage;