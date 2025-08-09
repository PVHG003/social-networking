import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import type { FunctionComponent } from "react";
import { Outlet } from "react-router-dom";

interface AuthLayoutProps {}

const AuthLayout: FunctionComponent<AuthLayoutProps> = () => {
  return (
    <div className="flex min-h-screen items-center justify-center bg-muted/10">
      <Card className="w-full max-w-md shadow-md">
        <CardHeader>
          <CardTitle className="text-center">Welcome Back</CardTitle>
        </CardHeader>
        <CardContent>
          <Outlet />
        </CardContent>
      </Card>
    </div>
  );
};

export default AuthLayout;
