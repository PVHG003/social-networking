import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import type { FunctionComponent } from "react";
import { Outlet } from "react-router-dom";

interface AuthLayoutProps {}

const AuthLayout: FunctionComponent<AuthLayoutProps> = () => {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <Card className="w-full max-w-md">
        <CardHeader>
          <CardTitle className="text-2xl font-bold text-center">
            Welcome to Social Media
          </CardTitle>
        </CardHeader>
        <CardContent>
          <Outlet />
        </CardContent>
      </Card>
    </div>
  );
};

export default AuthLayout;
