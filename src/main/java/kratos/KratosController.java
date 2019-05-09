package kratos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class KratosController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Kratos!";
    }

    @RequestMapping(path = "/request/{transactionId}", method = RequestMethod.GET)
    public String checkTransaction(@PathVariable String transactionId) {
        return "{\"chunks\": [\"1\"]}";
    }

    @RequestMapping(path = "/request/{transactionId}/chunk/{chunkId}", method = RequestMethod.GET)
    public ResponseEntity<Resource> transactionRequest(@PathVariable String transactionId, @PathVariable String chunkId)
            throws IOException {

        final String workDir = Paths.get("").toAbsolutePath().toString();
        final File file = new File(workDir + "/some.txt.zip");
        final InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        final HttpHeaders headers = new HttpHeaders();

        return ResponseEntity.ok().headers(headers).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
    }

    @RequestMapping(path = "/request/{transactionId}", method = RequestMethod.DELETE)
    public void deleteTransaction(@PathVariable String transactionId) {
        return;
    }
}