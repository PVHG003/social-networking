import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Bell, Search } from "lucide-react";
import { useState, type FunctionComponent } from "react";

interface NavbarProps {}

const Navbar: FunctionComponent<NavbarProps> = () => {
  const [searchQuery, setSearchQuery] = useState("");

  function handleSearch(event: React.KeyboardEvent<HTMLInputElement>): void {
    if (event.key === "Enter") {
      console.log("Searching for:", searchQuery);
      // TODO: trigger search API call
    }
  }

  return (
    <header className="flex items-center justify-between p-4">
      {/* Left: Search bar */}
      <div className="flex items-center gap-2">
        <Search className="h-5 w-5 text-muted-foreground" />
        <Input
          placeholder="Search..."
          className="w-64"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          onKeyDown={handleSearch}
        />
      </div>

      {/* Right: Actions */}
      <div className="flex items-center gap-4">
        <Button variant="ghost" size="icon">
          <Bell className="h-5 w-5" />
        </Button>
        <Avatar>
          <AvatarImage src="/placeholder-user.jpg" />
          <AvatarFallback>U</AvatarFallback>
        </Avatar>
      </div>
    </header>
  );
};

export default Navbar;
