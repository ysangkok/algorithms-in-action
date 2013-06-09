import localization.*;
import java.awt.event.*;

class Alignment3DEditDialog$2 implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
        System.out.println(Messages.getString("Alignment3DEditDialog.2") + Alignment3DEditDialog.this.xScroll.getValue());
        System.out.println(Messages.getString("Alignment3DEditDialog.3") + Alignment3DEditDialog.this.yScroll.getValue());
        System.out.println(Messages.getString("Alignment3DEditDialog.4") + Alignment3DEditDialog.this.zScroll.getValue());
        System.out.println(Messages.getString("Alignment3DEditDialog.5") + Alignment3DEditDialog.this.xTranslate.getValue());
        System.out.println(Messages.getString("Alignment3DEditDialog.6") + Alignment3DEditDialog.this.yTranslate.getValue());
        System.out.println(Messages.getString("Alignment3DEditDialog.7") + Alignment3DEditDialog.this.zTranslate.getValue());
    }
}