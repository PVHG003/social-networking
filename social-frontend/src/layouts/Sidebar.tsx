import { Button } from "@/components/ui/button";
import { Home, MessageCircle, User } from "lucide-react";
import type { FunctionComponent } from "react";
import { Link } from "react-router-dom";

interface SidebarProps {}

const Sidebar: FunctionComponent<SidebarProps> = () => {
  return (
    <aside className="w-64 border-r bg-muted/10 p-4 flex flex-col gap-2">
      <Button variant="ghost" asChild>
        <Link to="/">
          <Home className="mr-2 h-4 w-4" /> Home
        </Link>
      </Button>
      <Button variant="ghost" asChild>
        <Link to="/profile/me">
          <User className="mr-2 h-4 w-4" /> Profile
        </Link>
      </Button>
      <Button variant="ghost" asChild>
        <Link to="/chat">
          <MessageCircle className="mr-2 h-4 w-4" /> Chat
        </Link>
      </Button>
    </aside>
  );
};

export default Sidebar;
