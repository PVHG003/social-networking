import { ScrollArea } from "@/components/ui/scroll-area";
import { Separator } from "@/components/ui/separator";
import { SidebarProvider, SidebarTrigger } from "@/components/ui/sidebar";

import type { FunctionComponent } from "react";
import { Outlet } from "react-router-dom";

import { AppSidebar } from "./AppSidebar";
import Navbar from "./Navbar";

interface MainLayoutProps {}

const MainLayout: FunctionComponent<MainLayoutProps> = () => {
  return (
    <SidebarProvider>
      <AppSidebar />
      <div className="flex flex-col flex-1">
        <Navbar />

        <Separator />
        <ScrollArea className="flex-1 p-4">
          <Outlet />
        </ScrollArea>
      </div>
    </SidebarProvider>
  );
};

export default MainLayout;
