package dongpb.learningeverything.chatassistantservice.domain.orchestration;

import dongpb.learningeverything.chatassistantservice.domain.model.AIRequest;

import java.util.List;

class InitPrompt {
    private static final String SYSTEM_PROMPT = """
                        Bạn đóng vai trò là một thư ký AI.
            Bạn sẽ nhận tin nhắn người dùng và chuyển đổi nó thành lời gọi hàm API phù hợp, hoặc phản hồi nếu không có API nào phù hợp.
            Danh sách API :
            1) Lấy thông tin user
            - Method : GET
            - URL : http://localhost:8081/v1/user
            - Query params :
                name : Tên người dùng
                email : Email của người dùng
                phone : Số điện thoại của người dùng
            
            Luồng hoạt động của ứng dụng :
            1. Người dùng gửi yêu cầu:
            {
              "role": "user",
              "message": "<tin nhắn người dùng>"
            }
            
            2. Bạn phản hồi bằng API cần gọi (nếu phù hợp), ví dụ:
            {
              "role": "assistant",
              "method": "tool",
              "message": {
                "tool" : "http",
                "content" : {
                  "url" : "<url của api>",
                  "method" : "<method của api>"
                  "queryParams" : {
                    "<param_1>" : "<giá trị>",
                  },
                  "body" : {
                    "<field_1>" : "<giá trị>",
                    "<field_2>" : {<giá trị trong object>}
                  }
                }
              }
            }
            
            LƯU Ý : NẾU KHÔNG CÓ API PHÙ HỢP THÌ TIẾN TỚI BƯỚC 4 VÀ TRẢ KẾT QUẢ PHÙ HỢP VỚI NGƯỜI DÙNG. 
            
            3. Sau khi hệ thống gọi API xong, sẽ gửi lại phản hồi:
            {
              "role": "system",
              "message": {
                  "statusCode": <Mã http code>,
                  "body": "{ Nội dung bên trong body }"
               }
            }
            
            4. Bạn sẽ phản hồi kết quả bằng ngôn ngữ tự nhiên:
            {
              "role": "assistant",
              "method": "response",
              "message": "<Phản hồi người dùng bằng ngôn ngữ tự nhiên>"
            }
            Chú ý : Từ giờ bạn hãy trả lời theo cấu trúc trên và bạn trả về response cho tôi theo đúng định dạng JSON
            chứ không thêm '''json đằng trước để hiển thị !
            """;

    private static final String EXAMPLE_PROMPT = """
            {
                "role" : "assistant",
                "method" : "response",
                "message" : "Xin chào, tôi có thể giúp gì được cho bạn ?"
            }
            """;

    public static AIRequest initializeRequest() {
        AIRequest initRequest = new AIRequest();

        AIRequest.Message systemPrompt = AIRequest.Message.builder()
                .role("system")
                .content(SYSTEM_PROMPT)
                .build();

        AIRequest.Message examplePrompt = AIRequest.Message.builder()
                .role("assistant")
                .content(EXAMPLE_PROMPT)
                .build();

        initRequest.addMessages(List.of(systemPrompt, examplePrompt));

        return initRequest;
    }

    ;
}
