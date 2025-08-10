import type { FunctionComponent } from "react";
import { Card, CardContent, CardHeader } from "../ui/card";
import { Avatar, AvatarFallback, AvatarImage } from "../ui/avatar";
import { useNavigate } from "react-router-dom";
import { Heart, MessageCircleMore } from "lucide-react";
import { Separator } from "../ui/separator";

interface PostCardProps {
  id: string;
  username: string;
  handleName: string;
  profilePicture: string | null;
  content: string | null;
  postMedias: string[] | null;
  likeCounts: number | 0;
  commentCounts: number | 0;
}

const PostCard: FunctionComponent<PostCardProps> = ({
  id,
  username,
  handleName,
  profilePicture,
  content,
  postMedias,
  likeCounts,
  commentCounts,
}) => {
  const navigate = useNavigate();

  return (
    <Card
      className="max-w-lvh mx-auto m-4 p-4 cursor-pointer hover:shadow-lg transition"
      onClick={() => navigate(`/posts/${id}`)}
    >
      {/* Header */}
      <CardHeader className="flex flex-row items-center gap-3">
        <Avatar>
          <AvatarImage src={profilePicture ?? ""} alt={username} />
          <AvatarFallback>{username.charAt(0).toUpperCase()}</AvatarFallback>
        </Avatar>
        <div>
          <p className="font-semibold">{username}</p>
          <p className="text-sm text-gray-500">@{handleName}</p>
        </div>
      </CardHeader>

      {/* Content */}
      <CardContent>
        {content && <p className="mb-3">{content}</p>}

        {/* Media */}
        {postMedias && postMedias.length > 0 && (
          <div
            className="grid grid-cols-2 grid-rows-2 gap-3 cursor-pointer hover:shadow-lg transition"
            onClick={() => navigate(`/posts/${id}`)}
          >
            {postMedias.slice(0, 4).map((media, idx) => {
              const isLastVisible = idx === 3 && postMedias.length > 4;
              const extraCount = postMedias.length - 4;

              return (
                <div key={idx} className="relative">
                  {media.match(/\.(mp4|webm|ogg)$/i) ? (
                    <video
                      src={media}
                      controls
                      className="w-full h-full object-cover rounded-lg"
                    />
                  ) : (
                    <img
                      src={media}
                      alt={`post-media-${idx}`}
                      className="w-full h-full object-cover rounded-lg"
                    />
                  )}

                  {isLastVisible && (
                    <div className="absolute inset-0 bg-black/50 flex items-center justify-center rounded-lg">
                      <span className="text-white text-xl font-semibold">
                        +{extraCount}
                      </span>
                    </div>
                  )}
                </div>
              );
            })}
          </div>
        )}
      </CardContent>

      <div className="flex justify-evenly gap-3 px-2 pb-2 text-gray-600">
        <div className="flex items-center gap-2">
          <Heart
            size={30}
            color="red"
            className="fill-red-500 outline-red-500"
          />
          <span className="text-xl">{likeCounts.toLocaleString()}</span>
        </div>
        <div className="flex items-center gap-2">
          <MessageCircleMore size={30} />
          <span className="text-xl">{commentCounts.toLocaleString()}</span>
        </div>
      </div>
    </Card>
  );
};

export default PostCard;
