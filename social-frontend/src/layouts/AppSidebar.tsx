import {
  ChevronDownIcon,
  ChevronUpIcon,
  Home,
  MessageCircle,
  UserIcon,
} from "lucide-react";

import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import {
  Sidebar,
  SidebarContent,
  SidebarFooter,
  SidebarGroup,
  SidebarGroupContent,
  SidebarGroupLabel,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
  useSidebar,
} from "@/components/ui/sidebar";
import { useState } from "react";
import { Link } from "react-router-dom";

const items = [
  { title: "Home", url: "/", icon: Home },
  { title: "Profile", url: "/profile/me", icon: UserIcon },
  { title: "Chat", url: "/chat", icon: MessageCircle },
];

export function AppSidebar() {
  const isMobile = useSidebar();
  const [isOpen, setIsOpen] = useState(false);

  function handleLogout(e: React.MouseEvent<HTMLDivElement>): void {
    throw new Error("Function not implemented.");
  }

  return (
    <Sidebar>
      <SidebarContent>
        <SidebarGroup>
          <SidebarGroupLabel>Application</SidebarGroupLabel>
          <SidebarGroupContent>
            <SidebarMenu>
              {items.map((item) => (
                <SidebarMenuItem key={item.title}>
                  <SidebarMenuButton asChild>
                    <a href={item.url}>
                      <item.icon />
                      <span>{item.title}</span>
                    </a>
                  </SidebarMenuButton>
                </SidebarMenuItem>
              ))}
            </SidebarMenu>
          </SidebarGroupContent>
        </SidebarGroup>
      </SidebarContent>

      <SidebarFooter>
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <SidebarMenuButton
              size="lg"
              className="data-[state=open]:bg-sidebar-accent data-[state=open]:text-sidebar-accent-foreground"
              onClick={() => setIsOpen((prev) => !prev)}
            >
              <Avatar className="h-8 w-8 rounded-lg grayscale">
                <AvatarImage />
                <AvatarFallback className="rounded-lg">CN</AvatarFallback>
              </Avatar>
              <div className="grid flex-1 text-left text-sm leading-tight">
                <span className="truncate font-medium">username</span>
                <span className="text-muted-foreground truncate text-xs">
                  email@gmail.com
                </span>
              </div>
              {isOpen ? (
                <ChevronUpIcon className="ml-auto h-4 w-4 transition-transform duration-200" />
              ) : (
                <ChevronDownIcon className="ml-auto h-4 w-4 transition-transform duration-200" />
              )}
            </SidebarMenuButton>
          </DropdownMenuTrigger>

          <DropdownMenuContent
            className="w-(--radix-dropdown-menu-trigger-width) min-w-56 rounded-lg"
            side={isMobile ? "bottom" : "right"}
            align="end"
            sideOffset={4}
          >
            <DropdownMenuItem asChild>
              <Link to="/profile/me">Profile</Link>
            </DropdownMenuItem>
            <DropdownMenuItem asChild>
              <div onClick={handleLogout}>Logout</div>
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      </SidebarFooter>
    </Sidebar>
  );
}
