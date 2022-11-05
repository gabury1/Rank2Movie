namespace MovieManager
{
    partial class MainForm
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.label1 = new System.Windows.Forms.Label();
            this.btnManage = new System.Windows.Forms.Button();
            this.btnUpdate = new System.Windows.Forms.Button();
            this.btnLoad = new System.Windows.Forms.Button();
            this.btnTime = new System.Windows.Forms.Button();
            this.txtLog = new System.Windows.Forms.TextBox();
            this.txtImage = new System.Windows.Forms.TextBox();
            this.boxImage = new System.Windows.Forms.GroupBox();
            this.pnlLogin = new System.Windows.Forms.Panel();
            this.txtPw = new System.Windows.Forms.TextBox();
            this.txtId = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.chkLogin = new System.Windows.Forms.CheckBox();
            this.btnImage = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.chkEnable = new System.Windows.Forms.CheckBox();
            this.boxImage.SuspendLayout();
            this.pnlLogin.SuspendLayout();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("궁서", 36F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            this.label1.Location = new System.Drawing.Point(12, 27);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(396, 60);
            this.label1.TabIndex = 0;
            this.label1.Text = "무-비 매네쟈";
            // 
            // btnManage
            // 
            this.btnManage.Location = new System.Drawing.Point(774, 476);
            this.btnManage.Name = "btnManage";
            this.btnManage.Size = new System.Drawing.Size(134, 61);
            this.btnManage.TabIndex = 1;
            this.btnManage.Text = "DB 관리";
            this.btnManage.UseVisualStyleBackColor = true;
            // 
            // btnUpdate
            // 
            this.btnUpdate.Location = new System.Drawing.Point(634, 476);
            this.btnUpdate.Name = "btnUpdate";
            this.btnUpdate.Size = new System.Drawing.Size(134, 61);
            this.btnUpdate.TabIndex = 2;
            this.btnUpdate.Text = "업데이트 관리";
            this.btnUpdate.UseVisualStyleBackColor = true;
            // 
            // btnLoad
            // 
            this.btnLoad.Location = new System.Drawing.Point(494, 476);
            this.btnLoad.Name = "btnLoad";
            this.btnLoad.Size = new System.Drawing.Size(134, 61);
            this.btnLoad.TabIndex = 3;
            this.btnLoad.Text = "정보 로드";
            this.btnLoad.UseVisualStyleBackColor = true;
            // 
            // btnTime
            // 
            this.btnTime.Location = new System.Drawing.Point(494, 27);
            this.btnTime.Name = "btnTime";
            this.btnTime.Size = new System.Drawing.Size(414, 52);
            this.btnTime.TabIndex = 5;
            this.btnTime.Text = "시간";
            this.btnTime.UseVisualStyleBackColor = true;
            // 
            // txtLog
            // 
            this.txtLog.Enabled = false;
            this.txtLog.Location = new System.Drawing.Point(26, 135);
            this.txtLog.Multiline = true;
            this.txtLog.Name = "txtLog";
            this.txtLog.Size = new System.Drawing.Size(462, 402);
            this.txtLog.TabIndex = 6;
            this.txtLog.Text = resources.GetString("txtLog.Text");
            // 
            // txtImage
            // 
            this.txtImage.Location = new System.Drawing.Point(7, 54);
            this.txtImage.Name = "txtImage";
            this.txtImage.Size = new System.Drawing.Size(369, 27);
            this.txtImage.TabIndex = 7;
            // 
            // boxImage
            // 
            this.boxImage.Controls.Add(this.pnlLogin);
            this.boxImage.Controls.Add(this.chkLogin);
            this.boxImage.Controls.Add(this.btnImage);
            this.boxImage.Controls.Add(this.label2);
            this.boxImage.Controls.Add(this.txtImage);
            this.boxImage.Location = new System.Drawing.Point(494, 135);
            this.boxImage.Name = "boxImage";
            this.boxImage.Size = new System.Drawing.Size(414, 259);
            this.boxImage.TabIndex = 9;
            this.boxImage.TabStop = false;
            this.boxImage.Text = "이미지 설정";
            // 
            // pnlLogin
            // 
            this.pnlLogin.Controls.Add(this.txtPw);
            this.pnlLogin.Controls.Add(this.txtId);
            this.pnlLogin.Controls.Add(this.label5);
            this.pnlLogin.Controls.Add(this.label6);
            this.pnlLogin.Location = new System.Drawing.Point(6, 127);
            this.pnlLogin.Name = "pnlLogin";
            this.pnlLogin.Size = new System.Drawing.Size(376, 76);
            this.pnlLogin.TabIndex = 15;
            // 
            // txtPw
            // 
            this.txtPw.Location = new System.Drawing.Point(81, 43);
            this.txtPw.Name = "txtPw";
            this.txtPw.Size = new System.Drawing.Size(295, 27);
            this.txtPw.TabIndex = 16;
            // 
            // txtId
            // 
            this.txtId.Location = new System.Drawing.Point(81, 3);
            this.txtId.Name = "txtId";
            this.txtId.Size = new System.Drawing.Size(295, 27);
            this.txtId.TabIndex = 15;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(1, 43);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(69, 20);
            this.label5.TabIndex = 14;
            this.label5.Text = "비밀번호";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(1, 3);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(54, 20);
            this.label6.TabIndex = 13;
            this.label6.Text = "아이디";
            // 
            // chkLogin
            // 
            this.chkLogin.AutoSize = true;
            this.chkLogin.Checked = true;
            this.chkLogin.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chkLogin.Location = new System.Drawing.Point(13, 97);
            this.chkLogin.Name = "chkLogin";
            this.chkLogin.Size = new System.Drawing.Size(126, 24);
            this.chkLogin.TabIndex = 14;
            this.chkLogin.Text = "네이버 로그인";
            this.chkLogin.UseVisualStyleBackColor = true;
            // 
            // btnImage
            // 
            this.btnImage.Location = new System.Drawing.Point(277, 209);
            this.btnImage.Name = "btnImage";
            this.btnImage.Size = new System.Drawing.Size(105, 37);
            this.btnImage.TabIndex = 13;
            this.btnImage.Text = "저장";
            this.btnImage.UseVisualStyleBackColor = true;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(7, 31);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(121, 20);
            this.label2.TabIndex = 8;
            this.label2.Text = "기본 이미지 URL";
            // 
            // chkEnable
            // 
            this.chkEnable.AutoSize = true;
            this.chkEnable.Location = new System.Drawing.Point(817, 400);
            this.chkEnable.Name = "chkEnable";
            this.chkEnable.Size = new System.Drawing.Size(91, 24);
            this.chkEnable.TabIndex = 15;
            this.chkEnable.Text = "보호하기";
            this.chkEnable.UseVisualStyleBackColor = true;
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(928, 561);
            this.Controls.Add(this.chkEnable);
            this.Controls.Add(this.boxImage);
            this.Controls.Add(this.btnTime);
            this.Controls.Add(this.btnLoad);
            this.Controls.Add(this.btnUpdate);
            this.Controls.Add(this.btnManage);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.txtLog);
            this.Name = "MainForm";
            this.Text = "무-비 매니저";
            this.Load += new System.EventHandler(this.MainForm_Load);
            this.boxImage.ResumeLayout(false);
            this.boxImage.PerformLayout();
            this.pnlLogin.ResumeLayout(false);
            this.pnlLogin.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Label label1;
        private Button btnManage;
        private Button btnUpdate;
        private Button btnLoad;
        private Button btnTime;
        private TextBox txtLog;
        private TextBox txtImage;
        private GroupBox boxImage;
        private Panel pnlLogin;
        private TextBox txtPw;
        private TextBox txtId;
        private Label label5;
        private Label label6;
        private CheckBox chkLogin;
        private Button btnImage;
        private Label label2;
        private CheckBox chkEnable;
    }
}